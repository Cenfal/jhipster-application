package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.UserWebsiteService;
import com.mycompany.myapp.domain.UserWebsite;
import com.mycompany.myapp.repository.UserWebsiteRepository;
import com.mycompany.myapp.service.dto.UserWebsiteDTO;
import com.mycompany.myapp.service.mapper.UserWebsiteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserWebsite}.
 */
@Service
@Transactional
public class UserWebsiteServiceImpl implements UserWebsiteService {

    private final Logger log = LoggerFactory.getLogger(UserWebsiteServiceImpl.class);

    private final UserWebsiteRepository userWebsiteRepository;

    private final UserWebsiteMapper userWebsiteMapper;

    public UserWebsiteServiceImpl(UserWebsiteRepository userWebsiteRepository, UserWebsiteMapper userWebsiteMapper) {
        this.userWebsiteRepository = userWebsiteRepository;
        this.userWebsiteMapper = userWebsiteMapper;
    }

    @Override
    public UserWebsiteDTO save(UserWebsiteDTO userWebsiteDTO) {
        log.debug("Request to save UserWebsite : {}", userWebsiteDTO);
        UserWebsite userWebsite = userWebsiteMapper.toEntity(userWebsiteDTO);
        userWebsite = userWebsiteRepository.save(userWebsite);
        return userWebsiteMapper.toDto(userWebsite);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserWebsiteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserWebsites");
        return userWebsiteRepository.findAll(pageable)
            .map(userWebsiteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserWebsiteDTO> findOne(Long id) {
        log.debug("Request to get UserWebsite : {}", id);
        return userWebsiteRepository.findById(id)
            .map(userWebsiteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserWebsite : {}", id);
        userWebsiteRepository.deleteById(id);
    }
}
