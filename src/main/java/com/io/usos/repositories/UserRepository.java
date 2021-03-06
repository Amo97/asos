package com.io.usos.repositories;

import com.io.usos.models.Pracownik;
import com.io.usos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Pracownik findPracownikByUsername(String username);
}
