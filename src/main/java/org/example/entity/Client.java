package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column()
    private String name;
    @Column
    private String email;
}
