package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.example.entity.Cabinet;
import org.example.enums.Status;

import java.util.List;

public record BuildingDTO(
        Long id,

        String name,

        Status status,

        List<Cabinet> cabinetList
) {}
