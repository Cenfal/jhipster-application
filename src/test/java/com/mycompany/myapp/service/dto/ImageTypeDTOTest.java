package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ImageTypeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageTypeDTO.class);
        ImageTypeDTO imageTypeDTO1 = new ImageTypeDTO();
        imageTypeDTO1.setId(1L);
        ImageTypeDTO imageTypeDTO2 = new ImageTypeDTO();
        assertThat(imageTypeDTO1).isNotEqualTo(imageTypeDTO2);
        imageTypeDTO2.setId(imageTypeDTO1.getId());
        assertThat(imageTypeDTO1).isEqualTo(imageTypeDTO2);
        imageTypeDTO2.setId(2L);
        assertThat(imageTypeDTO1).isNotEqualTo(imageTypeDTO2);
        imageTypeDTO1.setId(null);
        assertThat(imageTypeDTO1).isNotEqualTo(imageTypeDTO2);
    }
}
