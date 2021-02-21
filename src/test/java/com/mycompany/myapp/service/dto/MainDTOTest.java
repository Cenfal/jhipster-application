package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MainDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MainDTO.class);
        MainDTO mainDTO1 = new MainDTO();
        mainDTO1.setId(1L);
        MainDTO mainDTO2 = new MainDTO();
        assertThat(mainDTO1).isNotEqualTo(mainDTO2);
        mainDTO2.setId(mainDTO1.getId());
        assertThat(mainDTO1).isEqualTo(mainDTO2);
        mainDTO2.setId(2L);
        assertThat(mainDTO1).isNotEqualTo(mainDTO2);
        mainDTO1.setId(null);
        assertThat(mainDTO1).isNotEqualTo(mainDTO2);
    }
}
