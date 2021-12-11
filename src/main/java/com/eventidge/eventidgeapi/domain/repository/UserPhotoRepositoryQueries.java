package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;

public interface UserPhotoRepositoryQueries {

    UserPhoto save(UserPhoto userPhoto);
    void delete(UserPhoto userPhoto);

}
