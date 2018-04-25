package com.stech.user.controller;

import com.stech.user.domain.UserDetails;
import com.stech.user.repository.MemoryRepository;
import com.stech.user.repository.UserAssembler;
import com.stech.user.repository.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(UserDetails.class)
@RequestMapping(value = "/home", produces = "application/json")

public class UserController {

    @Autowired
    private MemoryRepository memoryRepository;

    @Autowired
    private UserAssembler userAssembler;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Long> createUser(@RequestBody UserDetails user) {
        UserDetails uDetails = memoryRepository.create(user);
        UserResource ur = userAssembler.toResource(uDetails);
        return new ResponseEntity<>(ur.getResourceId(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<UserResource>> ListUsers() {
        List<UserDetails> userList = memoryRepository.findAll();
        return new ResponseEntity<>(userAssembler.toResourceCollection(userList), HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean wasDeleted = memoryRepository.delete(id);
        HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(responseStatus);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> findUser(@PathVariable Long id) {
        Optional<UserDetails> userDetails = memoryRepository.find(id);
        if (userDetails.isPresent()) {
            return new ResponseEntity<>(userAssembler.toResource(userDetails.get()), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserResource> updateUser(@PathVariable Long id, @RequestBody UserDetails user) {
        boolean updated = memoryRepository.updateUser(id, user);
        if (updated) {
            return findUser(id);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
