package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CommentHistory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CommentHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentHistoryRepository extends JpaRepository<CommentHistory, Long> {
}
