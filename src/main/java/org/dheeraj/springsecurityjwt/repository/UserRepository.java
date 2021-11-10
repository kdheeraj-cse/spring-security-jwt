package org.dheeraj.springsecurityjwt.repository;

import java.util.Optional;

import org.dheeraj.springsecurityjwt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUsername(String username);
}
