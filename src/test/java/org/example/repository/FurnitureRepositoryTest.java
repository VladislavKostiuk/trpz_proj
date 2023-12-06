package org.example.repository;

import org.example.entity.Cabinet;
import org.example.entity.Furniture;
import org.example.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FurnitureRepositoryTest {
    @Autowired
    private FurnitureRepository furnitureRepository;

    @BeforeEach
    void init() {
        Furniture furniture = new Furniture(1L, "testName", Status.NORMAL);

        furnitureRepository.deleteAll();
        furnitureRepository.save(furniture);
    }

    @Test
    void testSave_success() {
        Furniture furniture = new Furniture(2L, "testName2", Status.NICE);
        furnitureRepository.save(furniture);
        int furnitureAmount = furnitureRepository.findAll().size();
        assertEquals(2, furnitureAmount);
    }

    @Test
    void testFindById_Success() {
        Furniture furniture = furnitureRepository.findById(1L).orElseThrow();
        Long actualId = furniture.getId();
        assertEquals(1L, actualId);
    }

    @Test
    void testDeleteById_Success() {
        furnitureRepository.deleteById(1L);
        int furnitureAmount = furnitureRepository.findAll().size();
        assertEquals(0, furnitureAmount);
    }

}