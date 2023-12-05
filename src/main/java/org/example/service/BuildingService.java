package org.example.service;

import org.example.dto.BuildingDTO;


public interface BuildingService {
    BuildingDTO getBuildingById(Long id);
    void createBuilding(BuildingDTO buildingDTO);
    void deleteBuildingById(Long id);
    void updateBuilding(Long id, BuildingDTO buildingDTO);
}
