package com.example.springbootblogrestapi.repository;

import com.example.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//we don't need @Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategoryId(Long categoryId);
}
