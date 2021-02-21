package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MainTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Main.class);
        Main main1 = new Main();
        main1.setId(1L);
        Main main2 = new Main();
        main2.setId(main1.getId());
        assertThat(main1).isEqualTo(main2);
        main2.setId(2L);
        assertThat(main1).isNotEqualTo(main2);
        main1.setId(null);
        assertThat(main1).isNotEqualTo(main2);
    }
}
