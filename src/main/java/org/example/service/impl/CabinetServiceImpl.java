package org.example.service.impl;

import org.example.dto.CabinetDTO;
import org.example.entity.Building;
import org.example.entity.Cabinet;
import org.example.mapper.CabinetMapper;
import org.example.repository.CabinetRepository;
import org.example.service.CabinetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CabinetServiceImpl implements CabinetService {
    private final CabinetRepository cabinetRepository;
    private final CabinetMapper cabinetMapper;

    @Autowired
    public CabinetServiceImpl(CabinetRepository cabinetRepository, CabinetMapper cabinetMapper) {
        this.cabinetRepository = cabinetRepository;
        this.cabinetMapper = cabinetMapper;
    }

    @Override
    public CabinetDTO getCabinetById(Long id) {
        Cabinet cabinet = cabinetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cabinet with this id wasn't found"));
        return cabinetMapper.mapToCabinetDTO(cabinet);
    }

    @Override
    public void createCabinet(CabinetDTO cabinetDTO) {
        Cabinet cabinet = cabinetMapper.mapToCabinet(cabinetDTO);
        cabinetRepository.save(cabinet);
    }

    @Override
    public void deleteCabinetById(Long id) {
        cabinetRepository.deleteById(id);
    }

    @Override
    public void updateCabinet(Long id, CabinetDTO cabinetDTO) {
        Cabinet cabinet = cabinetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cabinet with this id wasn't found"));
        cabinet.setName(cabinetDTO.name());
        cabinet.setStatus(cabinetDTO.status());
        cabinet.setFurnitureList(cabinetDTO.furnitureList());
    }
}
