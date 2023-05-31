package com.aleexalvz.api.cashwise.service;

import com.aleexalvz.api.cashwise.model.UserModel;
import com.aleexalvz.api.cashwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel save(UserModel userModel) {
        System.out.println(userModel);;
        return userRepository.save(userModel);
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
