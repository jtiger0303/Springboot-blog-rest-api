package com.example.springbootblogrestapi.entity;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.HashSet;
import java.util.Set;

//@Data //Generate getters for all fields=Getter&Setter&RequiredArgsConstructor, doesn't need while loop,
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name="posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="content", nullable = false)
    private String content;

    @OneToMany(mappedBy="post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments=new HashSet<>();

    @ManyToOne(fetch =FetchType.LAZY) //whenever we load post JPA entity, then the Category won't load immediately
    @JoinColumn(name="category_id")
    private Category category; //many posts can have one category
}
