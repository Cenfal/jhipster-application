package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ImageTypeService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ImageTypeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ImageType}.
 */
@RestController
@RequestMapping("/api")
public class ImageTypeResource {

    private final Logger log = LoggerFactory.getLogger(ImageTypeResource.class);

    private static final String ENTITY_NAME = "imageType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImageTypeService imageTypeService;

    public ImageTypeResource(ImageTypeService imageTypeService) {
        this.imageTypeService = imageTypeService;
    }

    /**
     * {@code POST  /image-types} : Create a new imageType.
     *
     * @param imageTypeDTO the imageTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageTypeDTO, or with status {@code 400 (Bad Request)} if the imageType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/image-types")
    public ResponseEntity<ImageTypeDTO> createImageType(@RequestBody ImageTypeDTO imageTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ImageType : {}", imageTypeDTO);
        if (imageTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ImageTypeDTO result = imageTypeService.save(imageTypeDTO);
        return ResponseEntity.created(new URI("/api/image-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /image-types} : Updates an existing imageType.
     *
     * @param imageTypeDTO the imageTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageTypeDTO,
     * or with status {@code 400 (Bad Request)} if the imageTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the imageTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/image-types")
    public ResponseEntity<ImageTypeDTO> updateImageType(@RequestBody ImageTypeDTO imageTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ImageType : {}", imageTypeDTO);
        if (imageTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ImageTypeDTO result = imageTypeService.save(imageTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /image-types} : get all the imageTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageTypes in body.
     */
    @GetMapping("/image-types")
    public List<ImageTypeDTO> getAllImageTypes() {
        log.debug("REST request to get all ImageTypes");
        return imageTypeService.findAll();
    }

    /**
     * {@code GET  /image-types/:id} : get the "id" imageType.
     *
     * @param id the id of the imageTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/image-types/{id}")
    public ResponseEntity<ImageTypeDTO> getImageType(@PathVariable Long id) {
        log.debug("REST request to get ImageType : {}", id);
        Optional<ImageTypeDTO> imageTypeDTO = imageTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageTypeDTO);
    }

    /**
     * {@code DELETE  /image-types/:id} : delete the "id" imageType.
     *
     * @param id the id of the imageTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/image-types/{id}")
    public ResponseEntity<Void> deleteImageType(@PathVariable Long id) {
        log.debug("REST request to delete ImageType : {}", id);
        imageTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
