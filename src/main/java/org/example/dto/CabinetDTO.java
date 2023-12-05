package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.example.entity.Furniture;
import org.example.enums.Status;

import java.util.List;

public record CabinetDTO(
        Long id,
        String name,
        Status status,
        List<Furniture>furnitureList
) {
}
