package org.example.mapper;

import org.example.dto.FurnitureDTO;
import org.example.entity.Furniture;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FurnitureMapper {
    FurnitureDTO mapToFurnitureDTO(Furniture furniture);
    Furniture mapToFurniture(FurnitureDTO furnitureDTO);
}
