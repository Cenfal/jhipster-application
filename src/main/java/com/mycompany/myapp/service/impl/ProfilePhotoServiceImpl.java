package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ProfilePhotoService;
import com.mycompany.myapp.domain.ProfilePhoto;
import com.mycompany.myapp.repository.ProfilePhotoRepository;
import com.mycompany.myapp.service.dto.ProfilePhotoDTO;
import com.mycompany.myapp.service.mapper.ProfilePhotoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProfilePhoto}.
 */
@Service
@Transactional
public class ProfilePhotoServiceImpl implements ProfilePhotoService {

    private final Logger log = LoggerFactory.getLogger(ProfilePhotoServiceImpl.class);

    private final ProfilePhotoRepository profilePhotoRepository;

    private final ProfilePhotoMapper profilePhotoMapper;

    public ProfilePhotoServiceImpl(ProfilePhotoRepository profilePhotoRepository, ProfilePhotoMapper profilePhotoMapper) {
        this.profilePhotoRepository = profilePhotoRepository;
        this.profilePhotoMapper = profilePhotoMapper;
    }

    @Override
    public ProfilePhotoDTO save(ProfilePhotoDTO profilePhotoDTO) {
        log.debug("Request to save ProfilePhoto : {}", profilePhotoDTO);
        ProfilePhoto profilePhoto = profilePhotoMapper.toEntity(profilePhotoDTO);
        profilePhoto = profilePhotoRepository.save(profilePhoto);
        return profilePhotoMapper.toDto(profilePhoto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProfilePhotoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProfilePhotos");
        return profilePhotoRepository.findAll(pageable)
            .map(profilePhotoMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ProfilePhotoDTO> findOne(Long id) {
        log.debug("Request to get ProfilePhoto : {}", id);
        return profilePhotoRepository.findById(id)
            .map(profilePhotoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProfilePhoto : {}", id);
        profilePhotoRepository.deleteById(id);
    }
}
