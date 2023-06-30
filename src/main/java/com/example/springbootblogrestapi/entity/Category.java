package com.example.springbootblogrestapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name= "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade=CascadeType.ALL, orphanRemoval = true) //orphanRemoval=true(remove all the orphaned entities from the database table
    private List<Post> posts;

}
