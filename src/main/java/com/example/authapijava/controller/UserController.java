package com.example.authapijava.controller;

import com.example.authapijava.dtos.user.CreateUserDTO;
import com.example.authapijava.dtos.user.ReturnUserDTO;
import com.example.authapijava.dtos.user.UpdateUserDTO;
import com.example.authapijava.exceptions.EmailAlreadyExistsException;
import com.example.authapijava.exceptions.NotFoundException;
import com.example.authapijava.services.UserService;
import com.example.authapijava.utils.ValidRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;


    @PostMapping
    public ResponseEntity<ReturnUserDTO> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        ReturnUserDTO userExists = this.userService.getUserByEmail(createUserDTO.email());

        if(userExists != null){
            throw new EmailAlreadyExistsException();
        }

        System.out.println("createUserDTO = " + createUserDTO);
        return ResponseEntity.ok(this.userService.createUser(createUserDTO));
    }

    @GetMapping
    public List<ReturnUserDTO> getAllUsers(@RequestParam Integer numPage, Integer numRegisters) {
        return this.userService.getAllUsers(numPage, numRegisters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnUserDTO> getUser(@PathVariable String id) {
        return ResponseEntity.ok(this.userService.getUser(id));
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<ReturnUserDTO> getUserByEmail(@PathVariable String email) {
        ReturnUserDTO user = this.userService.getUserByEmail(email);
        if(user == null){
            throw new NotFoundException("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserDTO updateUserDTO) {
        ValidRequestDTO.isValid(updateUserDTO);
        System.out.println("updateUserDTO = " + updateUserDTO);
        this.userService.updateUser(id, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/email/{email}")
    @Transactional
    public ResponseEntity<Void> updateUserByEmail(@PathVariable String email, @RequestBody UpdateUserDTO updateUserDTO) {
        this.userService.updateUserByEmail(email, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
