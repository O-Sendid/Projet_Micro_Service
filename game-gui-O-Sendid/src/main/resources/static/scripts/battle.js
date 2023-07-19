let battle;

let trainerName;
let opponentName;

let trainerCurrentPokemon;
let opponentCurrentPokemon;

const skipAnimations = false;

const battleApiUrl = "http://app-2dbff853-c377-4e35-9b4d-a5213d6d572e.cleverapps.io";

async function startBattle(a, b){
    trainerName = a;
    opponentName = b;

    const result = await $.post(`${battleApiUrl}/battles?trainer=${trainerName}&opponent=${opponentName}`);
    battle = result;
    console.log(battle);
    await showMessage(`Starting battle between ${trainerName} and ${opponentName} !`);
    const realBattle=await $.get(`${battleApiUrl}/battles/${battle}`);
    
    battle=realBattle;
    
    console.log(battle);

    // battle starts, get first pokemon for each trainer !
    trainerCurrentPokemon = battle.trainer.teamtype[0];
    opponentCurrentPokemon = battle.oponent.teamtype[0];

    await pokemonEntersBattle(trainerName, trainerCurrentPokemon, false);
    await pokemonEntersBattle(opponentName, opponentCurrentPokemon, true);

    await refreshBattle();
}

async function pokemonEntersBattle(trainerName, pokemon, frontView){
    console.log((pokemon));
    await showMessage(`${trainerName}'s ${pokemon.name} enters battle !`);

    const pokemonSprite = frontView ? pokemon.sprites.front_default : pokemon.sprites.back_default;
    const pokemonImageSelector = `[id='${trainerName}-pokemon-img']`;
    $(pokemonImageSelector).attr("src", pokemonSprite);
    $(`#${trainerName}-pokemon-name`).text(pokemon.name);

    await animateCss(pokemonImageSelector, "zoomIn");
    updatePokemonView(trainerName, pokemon);
}

function updatePokemonView(trainerName, pokemon){

    let pokemonHpBar = $(`[id='${trainerName}-pokemon-hp']`);

    pokemonHpBar.text(pokemon.stats.hp);
    pokemonHpBar.attr("aria-valuemax",pokemon.stats.maxHp);
    pokemonHpBar.attr("aria-valuenow",pokemon.stats.hp);
    pokemonHpBar.css("width",`${pokemon.stats.hp * 100 / pokemon.stats.maxHp}%` );
}

async function pokemonExitsBattle(trainerName, pokemon){
    await showMessage(`${trainerName}'s ${pokemon.name} exits battle !`);

    updatePokemonView(trainerName, pokemon);

    const pokemonImageSelector = `[id='${trainerName}-pokemon-img']`;
    return animateCss(pokemonImageSelector, "zoomOut");
}

function checkBattleEnd(){
    // battle ends if all pokemons of a trainer are KO !
    if(battle.trainer.teamtype.every(poke => poke.alive === false)){
        // show the battle end
        showMessage(`${trainerName} lost the battle !`);
        // exitPokemon(trainerName, trainerCurrentPokemon);
        return true;
    }
    if(battle.oponent.teamtype.every(poke => poke.alive === false)){
        showMessage(`${trainerName} win the battle !`);
        // exitPokemon(opponentName, opponentCurrentPokemon);
        return true;
    }
}

async function refreshBattle(){
    updatePokemonView(trainerName, trainerCurrentPokemon);
    updatePokemonView(opponentName, opponentCurrentPokemon);

    if(checkBattleEnd()){
        return;
    }

    // checking for ko
    if(!trainerCurrentPokemon.alive){
        await showMessage(`${trainerName}'s ${trainerCurrentPokemon.name} is KO !`);
        await pokemonExitsBattle(trainerName, trainerCurrentPokemon);

        const firstAliveTrainerPokemon = battle.trainer.teamtype.find(poke => poke.alive == true);
        trainerCurrentPokemon = firstAliveTrainerPokemon;
        await pokemonEntersBattle(trainerName, trainerCurrentPokemon, false);
    }

    if(!opponentCurrentPokemon.alive){
        await showMessage(`${opponentName}'s ${opponentCurrentPokemon.name} is KO !`);
        await pokemonExitsBattle(opponentName, opponentCurrentPokemon);

        const firstAliveOpponentPokemon = battle.oponent.teamtype.find(poke => poke.alive == true);
        opponentCurrentPokemon = firstAliveOpponentPokemon;
        await pokemonEntersBattle(opponentName, opponentCurrentPokemon, true);
    }

    updateControls();

    if(battle.oponent.nextTurn){
        await showMessage(`This is ${opponentName}'s turn.`);
        // wait a bit, then execute an IA turn if necessary
        setTimeout(() => {
            iaTurn();
        }, 1000);
    }
    else{
        await showMessage(`This is ${trainerName}'s turn.`);
    }
}

async function iaTurn(){
    await showMessage(`${opponentCurrentPokemon.name} attacks !`);
    await sendAttack(opponentName);
    await animateAttack(opponentName, trainerName);
    refreshBattle();
}

function updateControls(){
    // check if player's turn
    const playersTurn = battle.trainer.nextTurn;

    if(playersTurn){
        enableControls();
    }
    else{
        disableControls();
    }
}

function enableControls(){
    $("#attack-btn").removeAttr("disabled");
}

function disableControls(){
    $("#attack-btn").attr("disabled","disabled");
}

async function playerCommand(command){
    disableControls();
    if("ATTACK" === command){
        await showMessage(`${trainerCurrentPokemon.name} attacks !`);
        await sendAttack(trainerName);
        await animateAttack(trainerName, opponentName);
    }

    refreshBattle();
}

async function animateAttack(attackingTrainer, defendingTrainer){
    console.log(`${attackingTrainer} attacks ${defendingTrainer}`);

    const attackingPokemonImageSelector = `[id='${attackingTrainer}-pokemon-img']`;
    const defendingPokemonImageSelector = `[id='${defendingTrainer}-pokemon-img']`;

    await animateCss(attackingPokemonImageSelector, "bounce");
    await animateCss(defendingPokemonImageSelector, "flash");
}

async function sendAttack(trainerName){
    const result = await $.post(`${battleApiUrl}/battles/${battle.uuid}/${trainerName}/attack`);
    console.log(result);
    updateBattleData(result);
}

function updateBattleData(data){
    battle = data;
    trainerCurrentPokemon = battle.trainer.teamtype.find(poke => poke.id === trainerCurrentPokemon.id);
    opponentCurrentPokemon = battle.oponent.teamtype.find(poke => poke.id === opponentCurrentPokemon.id);
}

async function animateCss(element, animationName){
    return new Promise(function(resolve, reject){

        if(skipAnimations){
            console.log(`skipping animation ${animationName} on ${element}`);
            return resolve();
        }

        console.log(`starting animation ${animationName} on ${element}`);

        const node = $(element);
        node.removeClass()
            .addClass('animated ' + animationName)
            .one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', () => {
                node.removeClass();
                resolve();
            });
    });
}

async function showMessage(message){
    const selector = "#message";
    console.log(message);
    $(selector).text("");

    if(skipAnimations){
        $(selector).text(message);
        return;
    }

    return new Promise(function(resolve, reject){
        let i = 0;
        let timer = setInterval(() => {
            if(i<message.length){
                $(selector).append(message.charAt(i));
                i++;
            }
            else{
                clearInterval(timer);
                resolve();
            }
        }, 50);
    });
}