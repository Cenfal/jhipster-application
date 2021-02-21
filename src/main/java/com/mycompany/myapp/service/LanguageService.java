package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.LanguageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Language}.
 */
public interface LanguageService {

    /**
     * Save a language.
     *
     * @param languageDTO the entity to save.
     * @return the persisted entity.
     */
    LanguageDTO save(LanguageDTO languageDTO);

    /**
     * Get all the languages.
     *
     * @return the list of entities.
     */
    List<LanguageDTO> findAll();


    /**
     * Get the "id" language.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LanguageDTO> findOne(Long id);

    /**
     * Delete the "id" language.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
