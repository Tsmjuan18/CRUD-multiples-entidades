package com.sena.database_connection.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.database_connection.dtos.PostDto;
import com.sena.database_connection.entities.Post;
import com.sena.database_connection.entities.User;
import com.sena.database_connection.repositories.UserRepository;
import com.sena.database_connection.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

    private PostService service;
    private UserRepository userRepository;
    
    public PostController(PostService service, UserRepository userRepository){ 
        this.service= service;
        this.userRepository= userRepository;
    }

    //Metodo Get para todos los Post
    @GetMapping
    public List<Post> Get(){
        return this.service.getAllPost();
    }
    

    //Metodo Get por Id

    @GetMapping("/{id}")
    public ResponseEntity<Post> getId(@PathVariable Long id){

        Optional<Post> postFound = this.service.getIdPost(id);

        if (postFound.isEmpty()) {
            return ResponseEntity.status(404).build();
            
        }

        return ResponseEntity.status(200).body(postFound.get());
    }

    //metodo crear

    @PostMapping
    public Post Create(@RequestBody PostDto body){

        Post post = new Post();

        post.setTitle(body.getTitle());
        post.setLikes(body.getLikes());
        post.setDescription(body.getDescription());
        Optional<User>user= userRepository.findById(body.getUserId());
        if (user.isPresent()) {

            post.setUser(user.get());
            
        }
        return this.service.create(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> Update(@PathVariable Long id, @RequestBody PostDto body){


        Post post = new Post();
        post.setId(id);
        post.setTitle(body.getTitle());
        post.setLikes(body.getLikes());
        post.setDescription(body.getDescription());

        Post postUpdated = this.service.update(id,post);


        if (postUpdated==null) {

            return ResponseEntity.status(404).build();
            
        }      

        return ResponseEntity.status(202).body(postUpdated);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> Delete(@PathVariable Long id){

        Post postDeleted = this.service.delete(id);

        if (postDeleted== null) {

            return ResponseEntity.status(404).build();
            
        }

        

         return ResponseEntity.status(200).body(postDeleted);


    }
    
}
