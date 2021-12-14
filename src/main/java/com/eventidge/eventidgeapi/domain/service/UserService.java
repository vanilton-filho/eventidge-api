package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.exception.BusinessException;
import com.eventidge.eventidgeapi.domain.exception.ConflictException;
import com.eventidge.eventidgeapi.domain.exception.UserNotFoundException;
import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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
        Optional<User> foundUserEmail = userRepository.findByEmail(email);
        Optional<User> foundUserCpf = userRepository.findByCpf(cpf);

        if (foundUserEmail.isPresent() && !foundUserEmail.get().equals(user)) {
            throw new BusinessException(String.format("Duplicate email: %s", email));
        }

        if (foundUserCpf.isPresent() && !foundUserCpf.get().equals((user))) {
            throw new BusinessException(String.format("Duplicate CPF: %s", cpf));
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
            user.setStatus(true);
            return saveUserPerson(user);
        } else {
            user.setStatus(false);
            return saveUserOrg(user);
        }
    }
}
