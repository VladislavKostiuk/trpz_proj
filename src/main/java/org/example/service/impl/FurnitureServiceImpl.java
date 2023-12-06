package org.example.service.impl;

import org.example.dto.FurnitureDTO;
import org.example.entity.Cabinet;
import org.example.entity.Furniture;
import org.example.mapper.FurnitureMapper;
import org.example.repository.FurnitureRepository;
import org.example.security.context.SecurityContext;
import org.example.service.FurnitureService;
import org.springframework.stereotype.Service;

@Service
public class FurnitureServiceImpl implements FurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final FurnitureMapper furnitureMapper;

    public FurnitureServiceImpl(FurnitureRepository furnitureRepository, FurnitureMapper furnitureMapper) {
        this.furnitureRepository = furnitureRepository;
        this.furnitureMapper = furnitureMapper;
    }

    @Override
    public FurnitureDTO getFurnitureById(Long id) {
        checkIfUserIsLogged();
        Furniture furniture = furnitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Furniture with this id wasn't found"));
        return furnitureMapper.mapToFurnitureDTO(furniture);
    }

    @Override
    public void createFurniture(FurnitureDTO furnitureDTO) {
        checkIfUserIsLogged();
        Furniture furniture = furnitureMapper.mapToFurniture(furnitureDTO);
        furnitureRepository.save(furniture);
    }

    @Override
    public void deleteFurnitureById(Long id) {
        checkIfUserIsLogged();
        furnitureRepository.deleteById(id);
    }

    @Override
    public void updateFurniture(Long id, FurnitureDTO furnitureDTO) {
        checkIfUserIsLogged();
        Furniture furniture = furnitureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Furniture with this id wasn't found"));
        furniture.setName(furnitureDTO.name());
        furniture.setStatus(furnitureDTO.status());
        furnitureRepository.save(furniture);
    }

    private void checkIfUserIsLogged() {
        if (SecurityContext.getUser() == null) {
            throw new IllegalStateException("User should be logged");
        }
    }
}
