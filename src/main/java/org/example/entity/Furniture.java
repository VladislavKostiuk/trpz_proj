package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.Status;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Furniture {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Status status;
}
