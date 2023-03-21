package com.carlosquintana.imageboard.repositories;

import com.carlosquintana.imageboard.models.entities.SubmissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionRepository extends CrudRepository<SubmissionEntity, Long> {}