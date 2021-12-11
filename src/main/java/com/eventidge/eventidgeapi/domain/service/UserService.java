package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.ConflictException;
import com.eventidge.eventidgeapi.domain.exception.UserNotFoundException;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private EmailService emailService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllPerson() {
        return userRepository.findAllPerson();
    }

    public List<User> findAllOrgs() {
        return userRepository.findAllOrgs();
    }

    public User findOrFail(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional
    public User saveUserPerson(User user) {
        userRepository.detach(user);
        var email = user.getEmail();
        var cpf = user.getPerson().getNaturalPerson().getCpf();
        Optional<User> userFound = userRepository.findByEmailOrCpf(email, cpf);

        if (userFound.isPresent() && !userFound.get().equals(user)) {
            throw new ConflictException("Duplicate Email or CPF");
        }

        user.confirmRegistration();
        return userRepository.save(user);

    }

    @Transactional
    public User saveUserOrg(User user) {
        user.confirmRegistration();
        return userRepository.save(user);
    }

    public User save(User user) {
        var userType = user.getPerson();
        if (userType != null) {
            return saveUserPerson(user);
        } else {
            return saveUserOrg(user);
        }
    }
}
