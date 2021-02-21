package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CommentHistoryService;
import com.mycompany.myapp.domain.CommentHistory;
import com.mycompany.myapp.repository.CommentHistoryRepository;
import com.mycompany.myapp.service.dto.CommentHistoryDTO;
import com.mycompany.myapp.service.mapper.CommentHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CommentHistory}.
 */
@Service
@Transactional
public class CommentHistoryServiceImpl implements CommentHistoryService {

    private final Logger log = LoggerFactory.getLogger(CommentHistoryServiceImpl.class);

    private final CommentHistoryRepository commentHistoryRepository;

    private final CommentHistoryMapper commentHistoryMapper;

    public CommentHistoryServiceImpl(CommentHistoryRepository commentHistoryRepository, CommentHistoryMapper commentHistoryMapper) {
        this.commentHistoryRepository = commentHistoryRepository;
        this.commentHistoryMapper = commentHistoryMapper;
    }

    @Override
    public CommentHistoryDTO save(CommentHistoryDTO commentHistoryDTO) {
        log.debug("Request to save CommentHistory : {}", commentHistoryDTO);
        CommentHistory commentHistory = commentHistoryMapper.toEntity(commentHistoryDTO);
        commentHistory = commentHistoryRepository.save(commentHistory);
        return commentHistoryMapper.toDto(commentHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentHistoryDTO> findAll() {
        log.debug("Request to get all CommentHistories");
        return commentHistoryRepository.findAll().stream()
            .map(commentHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CommentHistoryDTO> findOne(Long id) {
        log.debug("Request to get CommentHistory : {}", id);
        return commentHistoryRepository.findById(id)
            .map(commentHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CommentHistory : {}", id);
        commentHistoryRepository.deleteById(id);
    }
}
