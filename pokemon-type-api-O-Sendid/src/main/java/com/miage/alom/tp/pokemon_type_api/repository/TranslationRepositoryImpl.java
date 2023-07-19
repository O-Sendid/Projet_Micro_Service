package com.miage.alom.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.alom.tp.pokemon_type_api.bo.PokemonType;
import com.miage.alom.tp.pokemon_type_api.bo.Translation;

import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository{
	record Key(Locale locale, int pokemonId){} 

    private Map<Key, Translation> translations = new HashMap<TranslationRepositoryImpl.Key, Translation>();

    private ObjectMapper objectMapper;

    public TranslationRepositoryImpl() {
    
        try {
        	objectMapper = new ObjectMapper();
        	var english = new ClassPathResource("translations-en.json").getInputStream();
        	var french = new ClassPathResource("translations-fr.json").getInputStream();
        	objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var englishArray = objectMapper.readValue(english, Translation[].class);
            for (int i = 0; i < englishArray.length; i++) {
				var trans = englishArray[i];
				this.translations.put(new Key(Locale.ENGLISH,trans.id()), trans);
			}
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            var frenchArray = objectMapper.readValue(french, Translation[].class);
            for (int i = 0; i < frenchArray.length; i++) {
				var trans = frenchArray[i];
				this.translations.put(new Key(Locale.FRENCH,trans.id()), trans);
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@Override
	public String getPokemonName(int id, Locale locale) {
		try {
			locale.getLanguage();
			if (locale.getLanguage().equals("fr")) {
				Key key = new Key(Locale.FRENCH,id);
				return this.translations.get(key).name();
			}
			Key key = new Key(Locale.ENGLISH,id);
			return this.translations.get(key).name();
		} catch (Exception e) {
			Key key = new Key(Locale.ENGLISH,id);
			return this.translations.get(key).name();
		}
	}


}
