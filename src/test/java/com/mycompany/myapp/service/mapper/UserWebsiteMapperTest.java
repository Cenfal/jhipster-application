package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class UserWebsiteMapperTest {

    private UserWebsiteMapper userWebsiteMapper;

    @BeforeEach
    public void setUp() {
        userWebsiteMapper = new UserWebsiteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(userWebsiteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(userWebsiteMapper.fromId(null)).isNull();
    }
}
