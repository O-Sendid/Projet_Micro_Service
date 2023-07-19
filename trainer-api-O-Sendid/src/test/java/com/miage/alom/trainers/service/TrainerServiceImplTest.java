package com.miage.alom.trainers.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.miage.alom.trainers.bo.Trainer;
import com.miage.alom.trainers.repository.TrainerRepository;

class TrainerServiceImplTest {

    @Test
    void getAllTrainers_shouldCallTheRepository() {
        var trainerRepo = mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        trainerService.getAllTrainers();

        verify(trainerRepo).findAll();
    }

    @Test
    void getTrainer_shouldCallTheRepository() {
        var trainerRepo = mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        trainerService.getTrainer("Ash");

        verify(trainerRepo).findById("Ash");
    }

    @Test
    void createTrainer_shouldCallTheRepository() {
        var trainerRepo = mock(TrainerRepository.class);
        var trainerService = new TrainerServiceImpl(trainerRepo);

        var ash = new Trainer();
        ash.setPassword("test");
        trainerService.createTrainer(ash);

        verify(trainerRepo).save(ash);
    }

}