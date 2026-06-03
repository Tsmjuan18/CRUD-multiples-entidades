package com.sena.database_connection.services;

import org.springframework.stereotype.Service;

import com.sena.database_connection.repositories.PostRepository;

@Service
public class PostService {
    

    private PostRepository postRepository;

    public PostService (PostRepository postRepository){
        this.postRepository= postRepository;
    }


    
}
