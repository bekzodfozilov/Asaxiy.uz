package com.example.asaxiy_uz.Repository;

import com.example.asaxiy_uz.Dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByUsername(String username);
    boolean existsByPassword(String password);
    Optional<User> findByPassword(String password);

}
