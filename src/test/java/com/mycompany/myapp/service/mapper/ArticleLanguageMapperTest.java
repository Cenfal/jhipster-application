package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticleLanguageMapperTest {

    private ArticleLanguageMapper articleLanguageMapper;

    @BeforeEach
    public void setUp() {
        articleLanguageMapper = new ArticleLanguageMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(articleLanguageMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(articleLanguageMapper.fromId(null)).isNull();
    }
}
