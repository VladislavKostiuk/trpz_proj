package org.example.service.impl;

import org.example.dto.BuildingDTO;
import org.example.dto.CabinetDTO;
import org.example.entity.Building;
import org.example.entity.Cabinet;
import org.example.enums.Status;
import org.example.mapper.CabinetMapper;
import org.example.mapper.CabinetMapperImpl;
import org.example.repository.CabinetRepository;
import org.example.service.CabinetService;
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
        CabinetMapperImpl.class
})
class CabinetServiceImplTest {
    private CabinetService cabinetService;
    @Mock
    private CabinetRepository cabinetRepository;
    @Autowired
    private CabinetMapper cabinetMapper;
    private Cabinet testCabinet;
    private CabinetDTO testCabinetDTO;

    @BeforeEach
    void init() {
        cabinetService = new CabinetServiceImpl(cabinetRepository, cabinetMapper);
        testCabinet = new Cabinet(1L, "test name", Status.NORMAL, new ArrayList<>());
        testCabinetDTO = cabinetMapper.mapToCabinetDTO(testCabinet);
    }

    @Test
    void testGetCabinetById_Success() {
        when(cabinetRepository.findById(anyLong())).thenReturn(Optional.of(testCabinet));
        CabinetDTO actualCabinetDTO = cabinetService.getCabinetById(1L);
        assertEquals(testCabinetDTO, actualCabinetDTO);
        verify(cabinetRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetCabinetById_CabinetWasNotFound() {
        when(cabinetRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> cabinetService.getCabinetById(1L));
    }

    @Test
    void testCreateCabinet_Success() {
        cabinetService.createCabinet(testCabinetDTO);
        verify(cabinetRepository, times(1)).save(any());
    }

    @Test
    void testDeleteCabinetById_Success() {
        cabinetService.deleteCabinetById(1L);
        verify(cabinetRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void testUpdateCabinet_Success() {
        when(cabinetRepository.findById(anyLong())).thenReturn(Optional.of(testCabinet));
        cabinetService.updateCabinet(1L, testCabinetDTO);
        verify(cabinetRepository, times(1)).findById(anyLong());
        verify(cabinetRepository, times(1)).save(any());
    }

    @Test
    void testUpdateCabinet_CabinetWasNotFound() {
        when(cabinetRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> cabinetService.getCabinetById(1L));
    }

}