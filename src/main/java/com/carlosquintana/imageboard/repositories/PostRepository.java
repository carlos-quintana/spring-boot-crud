package com.carlosquintana.imageboard.repositories;

import com.carlosquintana.imageboard.models.entities.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
