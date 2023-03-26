package com.carlosquintana.imageboard.services.data;

import com.carlosquintana.imageboard.models.entities.CategoryEntity;
import com.carlosquintana.imageboard.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryDataAccess {
    @Autowired
    private CategoryRepository repository;

    public List<CategoryEntity> findAll() {
        Iterable<CategoryEntity> entities = repository.findAll();
        List<CategoryEntity> results = new ArrayList<CategoryEntity>();
        for (CategoryEntity entity : entities)
            results.add(entity);
        return results;
    }
}
