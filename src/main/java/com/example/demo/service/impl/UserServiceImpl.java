package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;mv
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        ProjectValidator.validateUser(user);
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User updated) {
        User existing = getById(id);
        existing.setUsername(updated.getUsername());
        existing.setEmail(updated.getEmail());
        existing.setRole(updated.getRole());
        return userRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
