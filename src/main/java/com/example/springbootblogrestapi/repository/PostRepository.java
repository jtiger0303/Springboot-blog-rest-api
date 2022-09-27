package com.example.springbootblogrestapi.repository;

import com.example.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//we don't need @Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
