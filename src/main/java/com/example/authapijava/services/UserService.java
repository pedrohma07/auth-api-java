package com.example.authapijava.services;

import com.example.authapijava.model.user.User;
import com.example.authapijava.dtos.user.CreateUserDTO;
import com.example.authapijava.dtos.user.ReturnUserDTO;
import com.example.authapijava.dtos.user.UpdateUserDTO;
import com.example.authapijava.exceptions.NotFoundException;
import com.example.authapijava.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ReturnUserDTO createUser(CreateUserDTO createUserDTO) {
        var passwordHash = passwordEncoder.encode(createUserDTO.password());
        User user = new User(createUserDTO.name(), createUserDTO.email(), passwordHash, createUserDTO.role());
        ReturnUserDTO returnUserDTO = new ReturnUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
        userRepository.save(user);
        return returnUserDTO;
    }


    public List<ReturnUserDTO> getAllUsers(Integer numPage, Integer numRegisters){
        // PageRequest initial page is 0
        Pageable pageRequest = PageRequest.of( numPage, numRegisters);
        Page<User> users = userRepository.findAll(pageRequest);
        return users.stream()
                .map(user -> new ReturnUserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getRole()))
                .collect(Collectors.toList());
    }

    public ReturnUserDTO getUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();
        return new ReturnUserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public ReturnUserDTO getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return new ReturnUserDTO(
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    user.getRole()
            );
        }
        return null;
    }

    public void updateUser(String id, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();

        user.setName(updateUserDTO.name() != null ? updateUserDTO.name() : user.getName());
        user.setPassword(updateUserDTO.password() != null ? updateUserDTO.password() : user.getPassword());
        user.setRole(updateUserDTO.role() != null ? updateUserDTO.role() : user.getRole());
        user.setUpdatedAt(new java.util.Date());

        userRepository.save(user);
    }

    public void updateUserByEmail(String email, UpdateUserDTO updateUserDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = optionalUser.get();

        user.setName(updateUserDTO.name() != null ? updateUserDTO.name() : user.getName());
        user.setPassword(updateUserDTO.password() != null ? updateUserDTO.password() : user.getPassword());
        user.setRole(updateUserDTO.role() != null ? updateUserDTO.role() : user.getRole());
        user.setUpdatedAt(new java.util.Date());

        userRepository.save(user);
    }

    public void deleteUser(String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        userRepository.delete(optionalUser.get());
    }
}
