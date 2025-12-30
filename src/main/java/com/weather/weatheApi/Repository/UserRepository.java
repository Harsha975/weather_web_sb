package com.weather.weatheApi.Repository;

import com.weather.weatheApi.DTO.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
}

