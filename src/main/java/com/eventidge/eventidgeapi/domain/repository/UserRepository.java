package com.eventidge.eventidgeapi.domain.repository;

import com.eventidge.eventidgeapi.domain.model.user.User;
import com.eventidge.eventidgeapi.domain.model.user.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    @Query("from User as u where u.email = :email or u.person.naturalPerson.cpf = :cpf")
    Optional<User> findByEmailOrCpf(String email, String cpf);

    @Query("select up from UserPhoto as up where up.user.id = :userId")
    Optional<UserPhoto> findPhotoById(Long userId);

    @Query("from User as u where u.organization is not null")
    List<User> findAllOrgs();

    @Query("from User as u where u.person is not null")
    List<User> findAllPerson();

}
