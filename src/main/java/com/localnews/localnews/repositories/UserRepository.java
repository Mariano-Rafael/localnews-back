package com.localnews.localnews.repositories;

import com.localnews.localnews.models.UserDTO;
import com.localnews.localnews.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByUsername(String username);

    Optional<UserModel> findByEmail(String email);

    @Query("SELECT new com.localnews.localnews.models.UserDTO(u.username, u.email) FROM UserModel u WHERE u.id = :id")
    Optional<UserDTO> findUsernameAndEmailById(@Param("id") Long id);

}
