package org.example.mapper;

import org.example.dto.CabinetDTO;
import org.example.entity.Cabinet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CabinetMapper {
    CabinetDTO mapToCabinetDTO(Cabinet cabinet);
    Cabinet mapToCabinet(CabinetDTO cabinetDTO);
}
