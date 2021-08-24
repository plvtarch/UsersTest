package com.plvtarch.UsersTest.controllers;

import com.plvtarch.UsersTest.entity.User;
import com.plvtarch.UsersTest.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class UserController {

    @Autowired
    UsersRepository usersRepository;


    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUser()
    {
        try {
        List<User> users = usersRepository.findAll();

        if(users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> user = usersRepository.findById(id);

        return user
                .map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {

            User newUser = usersRepository
                    .save(new User(user.getName(),user.getLogin(),user.getPassword(),
                            user.getEmail(), user.getBirthdate()));

            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            User newUser = userOptional.get();
            newUser.setName(user.getName());
            newUser.setLogin(user.getLogin());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            newUser.setBirthdate(user.getBirthdate());
            return new ResponseEntity<>(usersRepository.save(newUser), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            usersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            usersRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
