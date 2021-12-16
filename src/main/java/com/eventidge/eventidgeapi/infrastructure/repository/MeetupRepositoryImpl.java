package com.eventidge.eventidgeapi.infrastructure.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;
import com.eventidge.eventidgeapi.domain.repository.MeetupQrCodeRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MeetupRepositoryImpl implements MeetupQrCodeRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @Override
    public MeetupQrCode save(MeetupQrCode meetupQrCode) {
        return manager.merge(meetupQrCode);
    }

    @Transactional
    @Override
    public void delete(MeetupQrCode meetupQrCode) {
        manager.remove(meetupQrCode);
    }


}
