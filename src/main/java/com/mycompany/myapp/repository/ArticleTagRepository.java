package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ArticleTag;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ArticleTag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArticleTagRepository extends JpaRepository<ArticleTag, Long> {
}
