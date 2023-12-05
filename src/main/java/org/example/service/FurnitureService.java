package org.example.service;


import org.example.dto.FurnitureDTO;


public interface FurnitureService {
    FurnitureDTO getFurnitureById(Long id);
    void createFurniture(FurnitureDTO furnitureDTO);
    void deleteFurnitureById(Long id);
    void updateFurniture(Long id, FurnitureDTO furnitureDTO);
}
