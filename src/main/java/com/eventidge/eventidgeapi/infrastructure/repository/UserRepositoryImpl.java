package com.eventidge.eventidgeapi.infrastructure.repository;

import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;
import com.eventidge.eventidgeapi.domain.repository.UserPhotoRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserPhotoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public UserPhoto save(UserPhoto userPhoto) {
        return manager.merge(userPhoto);
    }

    @Transactional
    @Override
    public void delete(UserPhoto userPhoto) {
        manager.remove(userPhoto);
    }

}
