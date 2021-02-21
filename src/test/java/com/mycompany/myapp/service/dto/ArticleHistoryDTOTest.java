package com.mycompany.myapp.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleHistoryDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleHistoryDTO.class);
        ArticleHistoryDTO articleHistoryDTO1 = new ArticleHistoryDTO();
        articleHistoryDTO1.setId(1L);
        ArticleHistoryDTO articleHistoryDTO2 = new ArticleHistoryDTO();
        assertThat(articleHistoryDTO1).isNotEqualTo(articleHistoryDTO2);
        articleHistoryDTO2.setId(articleHistoryDTO1.getId());
        assertThat(articleHistoryDTO1).isEqualTo(articleHistoryDTO2);
        articleHistoryDTO2.setId(2L);
        assertThat(articleHistoryDTO1).isNotEqualTo(articleHistoryDTO2);
        articleHistoryDTO1.setId(null);
        assertThat(articleHistoryDTO1).isNotEqualTo(articleHistoryDTO2);
    }
}
