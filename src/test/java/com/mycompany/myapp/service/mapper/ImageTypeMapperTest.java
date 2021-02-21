package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageTypeMapperTest {

    private ImageTypeMapper imageTypeMapper;

    @BeforeEach
    public void setUp() {
        imageTypeMapper = new ImageTypeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(imageTypeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(imageTypeMapper.fromId(null)).isNull();
    }
}
