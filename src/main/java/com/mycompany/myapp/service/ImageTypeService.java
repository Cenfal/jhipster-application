package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ImageTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ImageType}.
 */
public interface ImageTypeService {

    /**
     * Save a imageType.
     *
     * @param imageTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ImageTypeDTO save(ImageTypeDTO imageTypeDTO);

    /**
     * Get all the imageTypes.
     *
     * @return the list of entities.
     */
    List<ImageTypeDTO> findAll();


    /**
     * Get the "id" imageType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ImageTypeDTO> findOne(Long id);

    /**
     * Delete the "id" imageType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
