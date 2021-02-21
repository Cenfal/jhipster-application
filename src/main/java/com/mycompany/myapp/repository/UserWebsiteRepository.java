package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.UserWebsite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UserWebsite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserWebsiteRepository extends JpaRepository<UserWebsite, Long> {
}
