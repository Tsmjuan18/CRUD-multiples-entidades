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
import com.sena.database_connection.dtos.RoleDto;
import com.sena.database_connection.entities.Role;
import com.sena.database_connection.entities.User;
import com.sena.database_connection.repositories.UserRepository;
import com.sena.database_connection.services.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    private UserRepository userRepository;
    private RoleService service;
    
    public RoleController(RoleService service, UserRepository userRepository){ 
        this.service= service;
        this.userRepository= userRepository;
    }

    //Metodo Get para todos los Post
    @GetMapping
    public List<Role> Get(){
        return this.service.getAllRoles();
    }
    

    //Metodo Get por Id

    @GetMapping("/{id}")
    public ResponseEntity<Role> getId(@PathVariable Long id){

        Optional<Role> roleFound = this.service.getId(id);

        if (roleFound.isEmpty()) {
            return ResponseEntity.status(404).build();
            
        }

        return ResponseEntity.status(200).body(roleFound.get());
    }

    //metodo crear

    @PostMapping
    public Role Create(@RequestBody RoleDto body){

        Role role = new Role();
        role.setName(body.getName());
        return this.service.create(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> Update(@PathVariable Long id, @RequestBody RoleDto body){


         Role role = new Role();
         role.setId(id);
         role.setName(body.getName());

        Role roleUpdated = this.service.update(id,role);


        if (roleUpdated==null) {

            return ResponseEntity.status(404).build();
            
        }      

        return ResponseEntity.status(202).body(roleUpdated);
    }   

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> Delete(@PathVariable Long id){

        Role roleDeleted= this.service.delete(id);

        if (roleDeleted== null) {

            return ResponseEntity.status(404).build();
            
        }    

         return ResponseEntity.status(200).body(roleDeleted);


    }
    
}
