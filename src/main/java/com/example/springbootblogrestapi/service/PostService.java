package com.example.springbootblogrestapi.service;

import com.example.springbootblogrestapi.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePosts(PostDto postDto, long id);

    void deletePostById(long id);
}
