package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTopicMapperTest {

    private ArticleTopicMapper articleTopicMapper;

    @BeforeEach
    public void setUp() {
        articleTopicMapper = new ArticleTopicMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(articleTopicMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(articleTopicMapper.fromId(null)).isNull();
    }
}
