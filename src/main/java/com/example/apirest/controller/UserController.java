package com.example.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apirest.entity.ExternalUser;
import com.example.apirest.entity.User;
import com.example.apirest.repository.UserRepository;
import com.example.apirest.service.ExternalUserService;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExternalUserService externalUserService;

    // Obtener todos los usuarios
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener todos los usuarios de una API externa
    @GetMapping("/external-users")
    public List<ExternalUser> getExternalUsers() {
        return externalUserService.getExternalUserInfo();
    }

    // Obtener un usuario por id
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Crear un nuevo usuario
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Actualizar un usuario
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEmail(userDetails.getEmail());
        user.setExtraInfo(userDetails.getExtraInfo());
        return userRepository.save(user);
    }

    // Eliminar un usuario
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }
}