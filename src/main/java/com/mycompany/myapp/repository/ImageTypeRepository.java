package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ImageType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ImageType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageTypeRepository extends JpaRepository<ImageType, Long> {
}
