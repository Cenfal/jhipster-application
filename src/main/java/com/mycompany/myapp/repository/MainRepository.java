package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Main;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Main entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MainRepository extends JpaRepository<Main, Long> {
}
