package com.carlosquintana.imageboard.services.data;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.models.entities.SubmissionEntity;
import com.carlosquintana.imageboard.repositories.SubmissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubmissionDataAccess {

    @Autowired
    private SubmissionRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<SubmissionDTO> findAll() {
        Iterable<SubmissionEntity> entities = repository.findAll();
        List<SubmissionDTO> results = new ArrayList<SubmissionDTO>();
        for (SubmissionEntity entity : entities)
            results.add(mapper.map(entity, SubmissionDTO.class));
        return results;
    }

    public SubmissionDTO findById(long id) {
        Optional<SubmissionEntity> result = repository.findById(id);
        return result == null ? null : mapper.map(result, SubmissionDTO.class);
    }

    public long save(SubmissionDTO newSubmission) {
        SubmissionEntity entity = mapper.map(newSubmission, SubmissionEntity.class);
        SubmissionEntity result = repository.save(entity);
        return result.getId();
    }

    public SubmissionDTO update(long id, SubmissionDTO submissionToUpdate) {
        System.out.println(submissionToUpdate);

        SubmissionEntity existingEntity = repository.findById(id).orElse(null);
        if (existingEntity == null) {
            // The requested entity doesn't exist, TODO exception
        }
        // Make sure the id of the updated submission is the same as the one that is being updated
        submissionToUpdate.setId(id);
        submissionToUpdate.setCreatedAt(existingEntity.getCreatedAt());
        SubmissionEntity entity = mapper.map(submissionToUpdate, SubmissionEntity.class);
        SubmissionEntity updatedSubmission = repository.save(entity);
        SubmissionDTO result = mapper.map(updatedSubmission, SubmissionDTO.class);
        System.out.println(result);
        return result;
    }

    public boolean delete(long id) {
        if (repository.findById(id).orElse(null) == null)
            return false;
        repository.deleteById(id);
        return true;
    }

}
