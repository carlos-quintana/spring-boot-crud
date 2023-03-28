package com.carlosquintana.imageboard.services.data;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.models.entities.SubmissionEntity;
import com.carlosquintana.imageboard.repositories.CategoryRepository;
import com.carlosquintana.imageboard.repositories.SubmissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubmissionDataAccess {

    @Autowired
    private SubmissionRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    // The model mapper has been disabled for the moment so that we can use custom methods to transform the Submission DTOs to Entities and the other way round that make a query to the database to return the name of the category that the Submission is assigned. This is while we find a better way to do it. Next place to look into is creating TypeMaps in the model mapper bean
    // @Autowired
    // private ModelMapper mapper;

    public List<SubmissionDTO> findAll() {
        Iterable<SubmissionEntity> entities = repository.findAll();
        List<SubmissionDTO> results = new ArrayList<SubmissionDTO>();
        for (SubmissionEntity entity : entities)
            // results.add(mapper.map(entity, SubmissionDTO.class));
            results.add(entityToDto(entity));
        return results;
    }

    public SubmissionDTO findById(long id) {
        SubmissionEntity result = repository.findById(id).orElse(null);
        return result == null ? null : entityToDto(result); //mapper.map(result, SubmissionDTO.class);
    }

    public long save(SubmissionDTO newSubmission) {
        // SubmissionEntity entity = mapper.map(newSubmission, SubmissionEntity.class);
        SubmissionEntity entity = dtoToEntity(newSubmission);
        SubmissionEntity result = repository.save(entity);
        return result.getId();
    }

    public SubmissionDTO update(long id, SubmissionDTO submissionToUpdate) {
        System.out.println(submissionToUpdate);
        // Validate that the target id is an existing submission
        SubmissionEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null)
            // The requested entity doesn't exist, TODO exception
            return null;
        // Validate that the category that was sent does exist
        if (categoryRepository.findById(submissionToUpdate.getCategory()).isEmpty())
            // The category id that was sent does not exist, TODO exception
            return null;
        submissionToUpdate.setId(id);
        submissionToUpdate.setCreatedAt(existingEntity.getCreatedAt());
        // SubmissionEntity entity = mapper.map(submissionToUpdate, SubmissionEntity.class);
        SubmissionEntity entity = dtoToEntity(submissionToUpdate);
        SubmissionEntity updatedSubmission = repository.save(entity);
        // SubmissionDTO result = mapper.map(updatedSubmission, SubmissionDTO.class);
        return entityToDto(updatedSubmission);
    }

    public boolean delete(long id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }

    private SubmissionDTO entityToDto(SubmissionEntity entity) {
        SubmissionDTO dto = new SubmissionDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setTags(entity.getTags());
        dto.setImg_source(entity.getImg_source());
        dto.setCategory(entity.getCategory().getId());
        dto.setCategoryName(entity.getCategory().getName());
        return dto;
    }

    private SubmissionEntity dtoToEntity(SubmissionDTO dto) {
        SubmissionEntity entity = new SubmissionEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setTags(dto.getTags());
        entity.setImg_source(dto.getImg_source());
        entity.setCategory(categoryRepository.findById(dto.getCategory()).orElse(null));
        return entity;
    }

}
