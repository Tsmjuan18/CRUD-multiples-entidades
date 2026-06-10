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
import com.sena.database_connection.dtos.ProfileDto;
import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.entities.User;
import com.sena.database_connection.repositories.UserRepository;
import com.sena.database_connection.services.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private UserRepository userRepository;
    private ProfileService service;
    
    public ProfileController(ProfileService service, UserRepository userRepository){ 
        this.service= service;
        this.userRepository=userRepository;
    }

   
    @GetMapping
    public List<Profile>Get(){
        return this.service.getAll();
    }
    

    //Metodo Get por Id

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getId(@PathVariable Long id){

        Optional<Profile> profileFound = this.service.getId(id);

        if (profileFound.isEmpty()) {
            return ResponseEntity.status(404).build();
            
        }

        return ResponseEntity.status(200).body(profileFound.get());
    }

    //metodo crear

    @PostMapping
    public Profile Create(@RequestBody ProfileDto body){

        Profile profile = new Profile();

        profile.setUsername(body.getUsername());
        profile.setDescription(body.getDescription());
        Optional<User> user= userRepository.findById(body.getUserId());

        if (user.isEmpty()) {
            return null;
            
        }
        profile.setUser(user.get());
       

        return this.service.create(profile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> Update(@PathVariable Long id, @RequestBody ProfileDto body){


        Profile profile = new Profile();
        profile.setId(id);
        profile.setUsername(body.getUsername());;
        profile.setDescription(body.getDescription());;

        Profile profileUpdated = this.service.update(id,profile);


        if (profileUpdated==null) {

            return ResponseEntity.status(404).build();
            
        }      

        return ResponseEntity.status(202).body(profileUpdated);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> Delete(@PathVariable Long id){

        Profile profileDeleted = this.service.delete(id);

        if (profileDeleted== null) {

            return ResponseEntity.status(404).build();
            
        }     

         return ResponseEntity.status(200).body(profileDeleted);
    }
    
}
