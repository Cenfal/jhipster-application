package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MainService;
import com.mycompany.myapp.domain.Main;
import com.mycompany.myapp.repository.MainRepository;
import com.mycompany.myapp.service.dto.MainDTO;
import com.mycompany.myapp.service.mapper.MainMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Main}.
 */
@Service
@Transactional
public class MainServiceImpl implements MainService {

    private final Logger log = LoggerFactory.getLogger(MainServiceImpl.class);

    private final MainRepository mainRepository;

    private final MainMapper mainMapper;

    public MainServiceImpl(MainRepository mainRepository, MainMapper mainMapper) {
        this.mainRepository = mainRepository;
        this.mainMapper = mainMapper;
    }

    @Override
    public MainDTO save(MainDTO mainDTO) {
        log.debug("Request to save Main : {}", mainDTO);
        Main main = mainMapper.toEntity(mainDTO);
        main = mainRepository.save(main);
        return mainMapper.toDto(main);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MainDTO> findAll() {
        log.debug("Request to get all Mains");
        return mainRepository.findAll().stream()
            .map(mainMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MainDTO> findOne(Long id) {
        log.debug("Request to get Main : {}", id);
        return mainRepository.findById(id)
            .map(mainMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Main : {}", id);
        mainRepository.deleteById(id);
    }
}
