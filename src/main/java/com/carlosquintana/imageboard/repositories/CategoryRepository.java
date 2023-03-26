package com.carlosquintana.imageboard.repositories;

import com.carlosquintana.imageboard.models.entities.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {}