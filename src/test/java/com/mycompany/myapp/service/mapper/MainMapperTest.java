package com.mycompany.myapp.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MainMapperTest {

    private MainMapper mainMapper;

    @BeforeEach
    public void setUp() {
        mainMapper = new MainMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mainMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mainMapper.fromId(null)).isNull();
    }
}
