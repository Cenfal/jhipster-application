package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.UserWebsiteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.UserWebsite}.
 */
public interface UserWebsiteService {

    /**
     * Save a userWebsite.
     *
     * @param userWebsiteDTO the entity to save.
     * @return the persisted entity.
     */
    UserWebsiteDTO save(UserWebsiteDTO userWebsiteDTO);

    /**
     * Get all the userWebsites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserWebsiteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userWebsite.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserWebsiteDTO> findOne(Long id);

    /**
     * Delete the "id" userWebsite.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
