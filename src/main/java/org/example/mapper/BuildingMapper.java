package org.example.mapper;

import org.example.dto.BuildingDTO;
import org.example.entity.Building;
import org.mapstruct.Mapper;

@Mapper
public interface BuildingMapper {
    BuildingDTO mapToBuildingDTO(Building building);
    Building mapToBuilding(BuildingDTO buildingDTO);
}
