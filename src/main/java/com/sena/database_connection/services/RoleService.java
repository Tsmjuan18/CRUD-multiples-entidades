package com.sena.database_connection.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.database_connection.entities.Role;
import com.sena.database_connection.repositories.RoleRepository;

@Service
public class RoleService {

    private RoleRepository repository;

    public RoleService(RoleRepository repository){
        this.repository= repository;
    }


    public List<Role> getAllRoles(){
        return this.repository.findAll();       
    }

    public Optional<Role> getId(Long id){
        return this.repository.findById(id);
    }

    public Role create (Role role){

        return this.repository.save(role);        

    }

    public Role update(Long id,Role role){

        Optional<Role> roleFound= repository.findById(id);

        if (roleFound.isEmpty()){

            return null;
        }
        return this.repository.save(role);

    }

    public Role delete (Long id){

        Optional<Role> roleDeleted= repository.findById(id);

        if (roleDeleted.isEmpty()) {

            return null;
            
        }

         this.repository.delete(roleDeleted.get());

         return (roleDeleted.get());

    }





    
}
