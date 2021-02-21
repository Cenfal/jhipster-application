package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.MainService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.service.dto.MainDTO;

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
 * REST controller for managing {@link com.mycompany.myapp.domain.Main}.
 */
@RestController
@RequestMapping("/api")
public class MainResource {

    private final Logger log = LoggerFactory.getLogger(MainResource.class);

    private static final String ENTITY_NAME = "main";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MainService mainService;

    public MainResource(MainService mainService) {
        this.mainService = mainService;
    }

    /**
     * {@code POST  /mains} : Create a new main.
     *
     * @param mainDTO the mainDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mainDTO, or with status {@code 400 (Bad Request)} if the main has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mains")
    public ResponseEntity<MainDTO> createMain(@RequestBody MainDTO mainDTO) throws URISyntaxException {
        log.debug("REST request to save Main : {}", mainDTO);
        if (mainDTO.getId() != null) {
            throw new BadRequestAlertException("A new main cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MainDTO result = mainService.save(mainDTO);
        return ResponseEntity.created(new URI("/api/mains/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mains} : Updates an existing main.
     *
     * @param mainDTO the mainDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mainDTO,
     * or with status {@code 400 (Bad Request)} if the mainDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mainDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mains")
    public ResponseEntity<MainDTO> updateMain(@RequestBody MainDTO mainDTO) throws URISyntaxException {
        log.debug("REST request to update Main : {}", mainDTO);
        if (mainDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MainDTO result = mainService.save(mainDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mainDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mains} : get all the mains.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mains in body.
     */
    @GetMapping("/mains")
    public List<MainDTO> getAllMains() {
        log.debug("REST request to get all Mains");
        return mainService.findAll();
    }

    /**
     * {@code GET  /mains/:id} : get the "id" main.
     *
     * @param id the id of the mainDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mainDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mains/{id}")
    public ResponseEntity<MainDTO> getMain(@PathVariable Long id) {
        log.debug("REST request to get Main : {}", id);
        Optional<MainDTO> mainDTO = mainService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mainDTO);
    }

    /**
     * {@code DELETE  /mains/:id} : delete the "id" main.
     *
     * @param id the id of the mainDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mains/{id}")
    public ResponseEntity<Void> deleteMain(@PathVariable Long id) {
        log.debug("REST request to delete Main : {}", id);
        mainService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
