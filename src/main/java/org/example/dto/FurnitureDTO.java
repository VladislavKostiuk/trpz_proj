package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.example.enums.Status;

public record FurnitureDTO(
        Long id,
        String name,
        Status status
) {
}
