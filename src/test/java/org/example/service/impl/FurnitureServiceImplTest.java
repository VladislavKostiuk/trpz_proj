package org.example.service.impl;

import org.example.dto.CabinetDTO;
import org.example.dto.FurnitureDTO;
import org.example.entity.Cabinet;
import org.example.entity.Furniture;
import org.example.enums.Status;
import org.example.mapper.FurnitureMapper;
import org.example.mapper.FurnitureMapperImpl;
import org.example.repository.FurnitureRepository;
import org.example.service.FurnitureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        FurnitureMapperImpl.class
})
class FurnitureServiceImplTest {
    private FurnitureService furnitureService;
    @Mock
    private FurnitureRepository furnitureRepository;
    @Autowired
    private FurnitureMapper furnitureMapper;
    private Furniture testFurniture;
    private FurnitureDTO testFurnitureDTO;

    @BeforeEach
    void init() {
        furnitureService = new FurnitureServiceImpl(furnitureRepository, furnitureMapper);
        testFurniture = new Furniture(1L, "test name", Status.NORMAL);
        testFurnitureDTO = furnitureMapper.mapToFurnitureDTO(testFurniture);
    }

    @Test
    void testGetFurnitureById_Success() {
        when(furnitureRepository.findById(anyLong())).thenReturn(Optional.of(testFurniture));
        FurnitureDTO actualFurnitureDTO = furnitureService.getFurnitureById(1L);
        assertEquals(testFurnitureDTO, actualFurnitureDTO);
        verify(furnitureRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetFurnitureById_FurnitureWasNotFound() {
        when(furnitureRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> furnitureService.getFurnitureById(1L));
    }

    @Test
    void testCreateFurniture_Success() {
        furnitureService.createFurniture(testFurnitureDTO);
        verify(furnitureRepository, times(1)).save(any());
    }

    @Test
    void testDeleteFurnitureById_Success() {
        furnitureService.deleteFurnitureById(1L);
        verify(furnitureRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testUpdateFurniture_Success() {
        when(furnitureRepository.findById(anyLong())).thenReturn(Optional.of(testFurniture));
        furnitureService.updateFurniture(1L, testFurnitureDTO);
        verify(furnitureRepository, times(1)).findById(anyLong());
        verify(furnitureRepository, times(1)).save(any());
    }

    @Test
    void testUpdateFurniture_FurnitureWasNotFound() {
        when(furnitureRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> furnitureService.getFurnitureById(1L));
    }
}