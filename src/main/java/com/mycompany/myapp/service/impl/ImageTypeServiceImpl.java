package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.ImageTypeService;
import com.mycompany.myapp.domain.ImageType;
import com.mycompany.myapp.repository.ImageTypeRepository;
import com.mycompany.myapp.service.dto.ImageTypeDTO;
import com.mycompany.myapp.service.mapper.ImageTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ImageType}.
 */
@Service
@Transactional
public class ImageTypeServiceImpl implements ImageTypeService {

    private final Logger log = LoggerFactory.getLogger(ImageTypeServiceImpl.class);

    private final ImageTypeRepository imageTypeRepository;

    private final ImageTypeMapper imageTypeMapper;

    public ImageTypeServiceImpl(ImageTypeRepository imageTypeRepository, ImageTypeMapper imageTypeMapper) {
        this.imageTypeRepository = imageTypeRepository;
        this.imageTypeMapper = imageTypeMapper;
    }

    @Override
    public ImageTypeDTO save(ImageTypeDTO imageTypeDTO) {
        log.debug("Request to save ImageType : {}", imageTypeDTO);
        ImageType imageType = imageTypeMapper.toEntity(imageTypeDTO);
        imageType = imageTypeRepository.save(imageType);
        return imageTypeMapper.toDto(imageType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageTypeDTO> findAll() {
        log.debug("Request to get all ImageTypes");
        return imageTypeRepository.findAll().stream()
            .map(imageTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ImageTypeDTO> findOne(Long id) {
        log.debug("Request to get ImageType : {}", id);
        return imageTypeRepository.findById(id)
            .map(imageTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ImageType : {}", id);
        imageTypeRepository.deleteById(id);
    }
}
