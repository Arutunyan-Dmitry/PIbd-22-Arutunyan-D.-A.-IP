package com.ulstu.SushiBar.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ulstu.SushiBar.user.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLoginIgnoreCase(String login);
}

