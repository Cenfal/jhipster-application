package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticleHistoryMapperTest {

    private ArticleHistoryMapper articleHistoryMapper;

    @BeforeEach
    public void setUp() {
        articleHistoryMapper = new ArticleHistoryMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(articleHistoryMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(articleHistoryMapper.fromId(null)).isNull();
    }
}
