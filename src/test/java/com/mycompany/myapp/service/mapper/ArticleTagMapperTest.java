package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTagMapperTest {

    private ArticleTagMapper articleTagMapper;

    @BeforeEach
    public void setUp() {
        articleTagMapper = new ArticleTagMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(articleTagMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(articleTagMapper.fromId(null)).isNull();
    }
}
