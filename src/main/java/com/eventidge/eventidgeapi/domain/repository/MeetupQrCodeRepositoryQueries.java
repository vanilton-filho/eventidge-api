package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.meetup.MeetupQrCode;

public interface MeetupQrCodeRepositoryQueries {

    MeetupQrCode save(MeetupQrCode meetupQrCode);
    void delete(MeetupQrCode meetupQrCode);
}
