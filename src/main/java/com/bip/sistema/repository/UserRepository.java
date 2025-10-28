package com.bip.sistema.repository;

import com.bip.sistema.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // busca por crachá (badge)
    User findByBadgeCode(Long badgeCode);
}
