package com.example.bellapizza.service.impl;

import com.example.bellapizza.model.User;
import com.example.bellapizza.model.enumeration.Role;
import com.example.bellapizza.model.exceptions.EmailAlreadyExistsException;
import com.example.bellapizza.model.exceptions.InvalidUserException;
import com.example.bellapizza.model.exceptions.PasswordsDoNotMatchException;
import com.example.bellapizza.repository.UserRepository;
import com.example.bellapizza.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> register(String name, String surname, String email, String password, String repeatpassword) {
        if(email.isEmpty() || email == null || password.isEmpty() || password == null){
            throw new IllegalArgumentException();
        }
        if(userRepository.findByEmail(email)!=null){
            throw new EmailAlreadyExistsException(email);
        }
        if(!password.equals(repeatpassword)){
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(email, password, name,surname, Role.USER);
        user.setName(name);
        user.setSurname(surname);
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public User login(String email, String password) {
        User user = this.userRepository.findByEmailAndPassword(email, password);
        if(user == null){
            throw new InvalidUserException();
        }
        return user;
    }

}
