package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ImageTypeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageType.class);
        ImageType imageType1 = new ImageType();
        imageType1.setId(1L);
        ImageType imageType2 = new ImageType();
        imageType2.setId(imageType1.getId());
        assertThat(imageType1).isEqualTo(imageType2);
        imageType2.setId(2L);
        assertThat(imageType1).isNotEqualTo(imageType2);
        imageType1.setId(null);
        assertThat(imageType1).isNotEqualTo(imageType2);
    }
}
