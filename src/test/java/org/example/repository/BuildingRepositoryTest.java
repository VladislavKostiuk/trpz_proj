package org.example.repository;

import org.example.entity.Building;
import org.example.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BuildingRepositoryTest {
    @Autowired
    private BuildingRepository buildingRepository;

    @BeforeEach
    void init() {
        Building building = new Building(1L, "testName", Status.NORMAL, new ArrayList<>());

        buildingRepository.deleteAll();
        buildingRepository.save(building);
    }

    @Test
    void testSave_success() {
        Building building = new Building(2L, "testName2", Status.NICE, new ArrayList<>());
        buildingRepository.save(building);
        int buildingAmount = buildingRepository.findAll().size();
        assertEquals(2, buildingAmount);
    }

    @Test
    void testFindById_Success() {
        Building building = buildingRepository.findById(1L).orElseThrow();
        Long actualId = building.getId();
        assertEquals(1L, actualId);
    }

    @Test
    void testDeleteById_Success() {
        buildingRepository.deleteById(1L);
        int buildingAmount = buildingRepository.findAll().size();
        assertEquals(0, buildingAmount);
    }
}