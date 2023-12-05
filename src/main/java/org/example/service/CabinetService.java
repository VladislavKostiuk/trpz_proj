package org.example.service;

import org.example.dto.CabinetDTO;


public interface CabinetService {
    CabinetDTO getCabinetById(Long id);
    void createCabinet(CabinetDTO cabinetDTO);
    void deleteCabinetById(Long id);
    void updateCabinet(Long id, CabinetDTO cabinetDTO);
}
