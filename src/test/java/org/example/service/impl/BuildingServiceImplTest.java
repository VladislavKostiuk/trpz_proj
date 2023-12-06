package org.example.service.impl;

import org.example.dto.BuildingDTO;
import org.example.entity.Building;
import org.example.enums.Status;
import org.example.mapper.BuildingMapper;
import org.example.mapper.BuildingMapperImpl;
import org.example.repository.BuildingRepository;
import org.example.service.BuildingService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        BuildingMapperImpl.class
})
class BuildingServiceImplTest {
    private BuildingService buildingService;
    @Mock
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingMapper buildingMapper;
    private Building testBuilding;
    private BuildingDTO testBuildingDTO;

    @BeforeEach
    void init() {
        buildingService = new BuildingServiceImpl(buildingRepository, buildingMapper);

        testBuilding = new Building(1L, "test name", Status.NORMAL, new ArrayList<>());
        testBuildingDTO = buildingMapper.mapToBuildingDTO(testBuilding);
    }

    @Test
    void testGetBuildingById_Success() {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(testBuilding));
        BuildingDTO actualBuildingDTO = buildingService.getBuildingById(1L);
        assertEquals(testBuildingDTO, actualBuildingDTO);
        verify(buildingRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetBuildingById_BuildingWasNotFound() {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> buildingService.getBuildingById(1L));
    }

    @Test
    void testCreateBuilding_Success() {
        buildingService.createBuilding(testBuildingDTO);
        verify(buildingRepository, times(1)).save(any());
    }

    @Test
    void testDeleteBuildingById_Success() {
        buildingService.deleteBuildingById(1L);
        verify(buildingRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testUpdateBuilding_Success() {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.of(testBuilding));
        buildingService.updateBuilding(1L, testBuildingDTO);
        verify(buildingRepository, times(1)).findById(anyLong());
        verify(buildingRepository, times(1)).save(any());
    }

    @Test
    void testUpdateBuilding_BuildingWasNotFound() {
        when(buildingRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> buildingService.getBuildingById(1L));
    }
}