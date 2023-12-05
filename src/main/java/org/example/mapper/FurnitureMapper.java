package org.example.mapper;

import org.example.dto.FurnitureDTO;
import org.example.entity.Furniture;
import org.mapstruct.Mapper;

@Mapper
public interface FurnitureMapper {
    FurnitureDTO mapToFurnitureDTO(Furniture furniture);
    Furniture mapToFurniture(FurnitureDTO furnitureDTO);
}
