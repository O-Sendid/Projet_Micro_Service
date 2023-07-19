package com.miage.alom.trainers.repository;

import org.springframework.data.repository.CrudRepository;

import com.miage.alom.trainers.bo.Trainer;

public interface TrainerRepository extends CrudRepository<Trainer, String>  {
}