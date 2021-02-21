package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ProfilePhotoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ProfilePhoto}.
 */
public interface ProfilePhotoService {

    /**
     * Save a profilePhoto.
     *
     * @param profilePhotoDTO the entity to save.
     * @return the persisted entity.
     */
    ProfilePhotoDTO save(ProfilePhotoDTO profilePhotoDTO);

    /**
     * Get all the profilePhotos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProfilePhotoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profilePhoto.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProfilePhotoDTO> findOne(Long id);

    /**
     * Delete the "id" profilePhoto.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
