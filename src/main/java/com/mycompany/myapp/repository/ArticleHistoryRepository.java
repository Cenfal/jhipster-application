package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ArticleHistory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArticleHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleHistoryRepository extends JpaRepository<ArticleHistory, Long> {
}
