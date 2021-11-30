package com.eventidge.eventidgeapi.domain.service;

import com.eventidge.eventidgeapi.domain.model.Person;
import com.eventidge.eventidgeapi.domain.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
