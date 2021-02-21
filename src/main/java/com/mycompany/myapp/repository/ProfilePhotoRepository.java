package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ProfilePhoto;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProfilePhoto entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilePhotoRepository extends JpaRepository<ProfilePhoto, Long> {
}
