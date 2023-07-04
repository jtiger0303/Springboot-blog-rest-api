package com.example.springbootblogrestapi.service;

import com.example.springbootblogrestapi.payload.PostDto;
import com.example.springbootblogrestapi.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostDto updatePosts(PostDto postDto, long id);

    void deletePostById(long id);

    List<PostDto> getPostsByCategory(Long categoryId);
}
