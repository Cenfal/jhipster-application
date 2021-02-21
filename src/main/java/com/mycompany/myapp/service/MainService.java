package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.MainDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Main}.
 */
public interface MainService {

    /**
     * Save a main.
     *
     * @param mainDTO the entity to save.
     * @return the persisted entity.
     */
    MainDTO save(MainDTO mainDTO);

    /**
     * Get all the mains.
     *
     * @return the list of entities.
     */
    List<MainDTO> findAll();


    /**
     * Get the "id" main.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MainDTO> findOne(Long id);

    /**
     * Delete the "id" main.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
