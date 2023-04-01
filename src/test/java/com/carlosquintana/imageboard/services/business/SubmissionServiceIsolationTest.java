package com.carlosquintana.imageboard.services.business;

import com.carlosquintana.imageboard.models.dto.SubmissionDTO;
import com.carlosquintana.imageboard.services.data.SubmissionDataAccess;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubmissionServiceIsolationTest {

    @Mock
    private SubmissionDataAccess dataAccessService;

    @InjectMocks
    private SubmissionService service;

    @Test
    public void findUserById() {
        SubmissionDTO result = new SubmissionDTO();
        result.setId(1L);
        result.setTitle("Title");
        when(dataAccessService.findById(anyLong())).thenReturn(result);
        assertEquals(result, service.findById(1L));
    }

    @Test
    public void findNonExistingUserById() {
        when(dataAccessService.findById(1L)).thenThrow(
                new NoSuchElementException("Couldn't find the submission id"));
        Throwable exception = assertThrows(NoSuchElementException.class,
                () -> service.findById(1L));
        assertEquals("Couldn't find the submission id", exception.getMessage());
    }

}