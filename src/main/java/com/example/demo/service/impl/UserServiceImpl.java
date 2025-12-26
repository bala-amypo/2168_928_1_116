// package com.example.demo.service.impl;

// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.UserService;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public User create(User user) {
//         return userRepository.save(user);
//     }

//     @Override
//     public User update(Long id, User updated) {
//         User existing = userRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("User not found"));

//         existing.setUsername(updated.getUsername());
//         existing.setEmail(updated.getEmail());
//         existing.setRole(updated.getRole());

//         return userRepository.save(existing);
//     }

//     @Override
//     public User getById(Long id) {
//         return userRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("User not found"));
//     }

//     @Override
//     public List<User> getAll() {
//         return userRepository.findAll();
//     }

//     @Override
//     public void delete(Long id) {
//         userRepository.deleteById(id);
//     }
// }
