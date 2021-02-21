package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.UserWebsiteService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.UserWebsiteDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.UserWebsite}.
 */
@RestController
@RequestMapping("/api")
public class UserWebsiteResource {

    private final Logger log = LoggerFactory.getLogger(UserWebsiteResource.class);

    private static final String ENTITY_NAME = "userWebsite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserWebsiteService userWebsiteService;

    public UserWebsiteResource(UserWebsiteService userWebsiteService) {
        this.userWebsiteService = userWebsiteService;
    }

    /**
     * {@code POST  /user-websites} : Create a new userWebsite.
     *
     * @param userWebsiteDTO the userWebsiteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userWebsiteDTO, or with status {@code 400 (Bad Request)} if the userWebsite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-websites")
    public ResponseEntity<UserWebsiteDTO> createUserWebsite(@RequestBody UserWebsiteDTO userWebsiteDTO) throws URISyntaxException {
        log.debug("REST request to save UserWebsite : {}", userWebsiteDTO);
        if (userWebsiteDTO.getId() != null) {
            throw new BadRequestAlertException("A new userWebsite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserWebsiteDTO result = userWebsiteService.save(userWebsiteDTO);
        return ResponseEntity.created(new URI("/api/user-websites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-websites} : Updates an existing userWebsite.
     *
     * @param userWebsiteDTO the userWebsiteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userWebsiteDTO,
     * or with status {@code 400 (Bad Request)} if the userWebsiteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userWebsiteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-websites")
    public ResponseEntity<UserWebsiteDTO> updateUserWebsite(@RequestBody UserWebsiteDTO userWebsiteDTO) throws URISyntaxException {
        log.debug("REST request to update UserWebsite : {}", userWebsiteDTO);
        if (userWebsiteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserWebsiteDTO result = userWebsiteService.save(userWebsiteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userWebsiteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-websites} : get all the userWebsites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userWebsites in body.
     */
    @GetMapping("/user-websites")
    public ResponseEntity<List<UserWebsiteDTO>> getAllUserWebsites(Pageable pageable) {
        log.debug("REST request to get a page of UserWebsites");
        Page<UserWebsiteDTO> page = userWebsiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-websites/:id} : get the "id" userWebsite.
     *
     * @param id the id of the userWebsiteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userWebsiteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-websites/{id}")
    public ResponseEntity<UserWebsiteDTO> getUserWebsite(@PathVariable Long id) {
        log.debug("REST request to get UserWebsite : {}", id);
        Optional<UserWebsiteDTO> userWebsiteDTO = userWebsiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userWebsiteDTO);
    }

    /**
     * {@code DELETE  /user-websites/:id} : delete the "id" userWebsite.
     *
     * @param id the id of the userWebsiteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-websites/{id}")
    public ResponseEntity<Void> deleteUserWebsite(@PathVariable Long id) {
        log.debug("REST request to delete UserWebsite : {}", id);
        userWebsiteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
