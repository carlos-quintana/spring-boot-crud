package com.carlosquintana.imageboard.services.data;

import com.carlosquintana.imageboard.models.dto.PostDTO;
import com.carlosquintana.imageboard.models.entities.PostEntity;
import com.carlosquintana.imageboard.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostDataAccessData {

    @Autowired
    private PostRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<PostDTO> findAll() {
        Iterable<PostEntity> entities = repository.findAll();
        List<PostDTO> results = new ArrayList<PostDTO>();
        for (PostEntity entity : entities)
            results.add(mapper.map(entity, PostDTO.class));
        return results;
    }

    public PostDTO findById(long id) {
        Optional<PostEntity> result = repository.findById(id);
        return result == null ? null : mapper.map(result, PostDTO.class);
    }

    public long save(PostDTO submittedPost) {
        PostEntity entity = mapper.map(submittedPost, PostEntity.class);
        PostEntity result = repository.save(entity);
        return result.getId();
    }

    public PostDTO update(long id, PostDTO postToUpdate) {
        System.out.println(postToUpdate);
        PostEntity entity = mapper.map(postToUpdate, PostEntity.class);
        PostEntity updatedEntity = repository.save(entity);
        PostDTO result = mapper.map(updatedEntity, PostDTO.class);
        System.out.println(result);
        return result;
    }

    public boolean delete(long id) {
        repository.deleteById(id);
        return true;
    }

}
