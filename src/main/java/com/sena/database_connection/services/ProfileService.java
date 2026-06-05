package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Profile;
import com.sena.database_connection.repositories.ProfileRepository;


@Service
public class ProfileService {

    private ProfileRepository repository;

    public ProfileService(ProfileRepository repository){

        this.repository = repository;

    }

    public List<Profile> getAll(){

        return this.repository.findAll();

    }

    public Optional<Profile> getId(Long id){

        return this.repository.findById(id);

    }

    public Profile create (Profile profile){

        return this.repository.save(profile);


    }

    public Profile update(Profile profile){

        Optional<Profile> profileFound = this.getId(profile.getId());

        if (profileFound.isEmpty()) {

            return null;
            
        }

        return this.repository.save(profile);

    }

    public Profile delete(Long id){

        Optional<Profile> profileFound = this.getId(id);

        if (profileFound.isEmpty()) {
            
            return null;
        }

         this.repository.delete(profileFound.get());
         return profileFound.get();


    }





    
}
