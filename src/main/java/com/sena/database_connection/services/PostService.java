package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Post;
import com.sena.database_connection.repositories.PostRepository;

@Service
public class PostService {
    

    private PostRepository postRepository;

    public PostService (PostRepository postRepository){
        this.postRepository= postRepository;
    }

    public List<Post> getAllPost(){

        return this.postRepository.findAll();

    }

    public Optional<Post> getIdPost(long id){

        return this.postRepository.findById(id);
    }

    public Post create  (Post post){
        return this.postRepository.save(post);
    }

    public Post update(Post post){
        Optional<Post> postFound = this.getIdPost(post.getId());

        if (postFound.isEmpty()) {

            return null;
            
        }

        return this.postRepository.save(post);
    }

    public Post delete (Long id){
        Optional <Post> postFound = this.getIdPost(id);

        if (postFound.isEmpty()) {

            return null;
            
        }

         this.postRepository.delete(postFound.get());

         return postFound.get();

    }

    






    
}
