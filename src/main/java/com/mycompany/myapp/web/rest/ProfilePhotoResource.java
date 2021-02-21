package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.ProfilePhotoService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.ProfilePhotoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ProfilePhoto}.
 */
@RestController
@RequestMapping("/api")
public class ProfilePhotoResource {

    private final Logger log = LoggerFactory.getLogger(ProfilePhotoResource.class);

    private static final String ENTITY_NAME = "profilePhoto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProfilePhotoService profilePhotoService;

    public ProfilePhotoResource(ProfilePhotoService profilePhotoService) {
        this.profilePhotoService = profilePhotoService;
    }

    /**
     * {@code POST  /profile-photos} : Create a new profilePhoto.
     *
     * @param profilePhotoDTO the profilePhotoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new profilePhotoDTO, or with status {@code 400 (Bad Request)} if the profilePhoto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/profile-photos")
    public ResponseEntity<ProfilePhotoDTO> createProfilePhoto(@RequestBody ProfilePhotoDTO profilePhotoDTO) throws URISyntaxException {
        log.debug("REST request to save ProfilePhoto : {}", profilePhotoDTO);
        if (profilePhotoDTO.getId() != null) {
            throw new BadRequestAlertException("A new profilePhoto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProfilePhotoDTO result = profilePhotoService.save(profilePhotoDTO);
        return ResponseEntity.created(new URI("/api/profile-photos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /profile-photos} : Updates an existing profilePhoto.
     *
     * @param profilePhotoDTO the profilePhotoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated profilePhotoDTO,
     * or with status {@code 400 (Bad Request)} if the profilePhotoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the profilePhotoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/profile-photos")
    public ResponseEntity<ProfilePhotoDTO> updateProfilePhoto(@RequestBody ProfilePhotoDTO profilePhotoDTO) throws URISyntaxException {
        log.debug("REST request to update ProfilePhoto : {}", profilePhotoDTO);
        if (profilePhotoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProfilePhotoDTO result = profilePhotoService.save(profilePhotoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, profilePhotoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /profile-photos} : get all the profilePhotos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of profilePhotos in body.
     */
    @GetMapping("/profile-photos")
    public ResponseEntity<List<ProfilePhotoDTO>> getAllProfilePhotos(Pageable pageable) {
        log.debug("REST request to get a page of ProfilePhotos");
        Page<ProfilePhotoDTO> page = profilePhotoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /profile-photos/:id} : get the "id" profilePhoto.
     *
     * @param id the id of the profilePhotoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the profilePhotoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/profile-photos/{id}")
    public ResponseEntity<ProfilePhotoDTO> getProfilePhoto(@PathVariable Long id) {
        log.debug("REST request to get ProfilePhoto : {}", id);
        Optional<ProfilePhotoDTO> profilePhotoDTO = profilePhotoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(profilePhotoDTO);
    }

    /**
     * {@code DELETE  /profile-photos/:id} : delete the "id" profilePhoto.
     *
     * @param id the id of the profilePhotoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/profile-photos/{id}")
    public ResponseEntity<Void> deleteProfilePhoto(@PathVariable Long id) {
        log.debug("REST request to delete ProfilePhoto : {}", id);
        profilePhotoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
