package org.example.service.impl;

import org.example.dto.BuildingDTO;
import org.example.entity.Building;
import org.example.mapper.BuildingMapper;
import org.example.repository.BuildingRepository;
import org.example.security.context.SecurityContext;
import org.example.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
    }

    @Override
    public BuildingDTO getBuildingById(Long id) {
        checkIfUserIsLogged();
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Building with this id wasn't found"));
        return buildingMapper.mapToBuildingDTO(building);
    }

    @Override
    public void createBuilding(BuildingDTO buildingDTO) {
        checkIfUserIsLogged();
        Building building = buildingMapper.mapToBuilding(buildingDTO);
        buildingRepository.save(building);
    }

    @Override
    public void deleteBuildingById(Long id) {
        checkIfUserIsLogged();
        buildingRepository.deleteById(id);
    }

    @Override
    public void updateBuilding(Long id, BuildingDTO buildingDTO) {
        checkIfUserIsLogged();
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Building with this id wasn't found"));
        building.setName(buildingDTO.name());
        building.setStatus(buildingDTO.status());
        building.setCabinetList(buildingDTO.cabinetList());
        buildingRepository.save(building);
    }

    private void checkIfUserIsLogged() {
        if (SecurityContext.getUser() == null) {
            throw new IllegalStateException("User should be logged");
        }
    }
}
