package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ArticleLanguage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArticleLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleLanguageRepository extends JpaRepository<ArticleLanguage, Long> {
}
