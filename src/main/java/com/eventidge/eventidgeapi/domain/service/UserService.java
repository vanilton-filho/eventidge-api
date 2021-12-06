package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.UserNotFoundException;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOrFail(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }
}
