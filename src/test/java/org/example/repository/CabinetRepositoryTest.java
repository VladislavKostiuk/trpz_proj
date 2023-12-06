package org.example.repository;

import org.example.entity.Building;
import org.example.entity.Cabinet;
import org.example.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CabinetRepositoryTest {
    @Autowired
    private CabinetRepository cabinetRepository;

    @BeforeEach
    void init() {
        Cabinet cabinet = new Cabinet(1L, "testName", Status.NORMAL, new ArrayList<>());

        cabinetRepository.deleteAll();
        cabinetRepository.save(cabinet);
    }

    @Test
    void testSave_success() {
        Cabinet cabinet = new Cabinet(2L, "testName2", Status.NICE, new ArrayList<>());
        cabinetRepository.save(cabinet);
        int cabinetAmount = cabinetRepository.findAll().size();
        assertEquals(2, cabinetAmount);
    }

    @Test
    void testFindById_Success() {
        Cabinet cabinet = cabinetRepository.findById(1L).orElseThrow();
        Long actualId = cabinet.getId();
        assertEquals(1L, actualId);
    }

    @Test
    void testDeleteById_Success() {
        cabinetRepository.deleteById(1L);
        int cabinetAmount = cabinetRepository.findAll().size();
        assertEquals(0, cabinetAmount);
    }

}