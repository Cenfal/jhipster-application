package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleHistoryTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleHistory.class);
        ArticleHistory articleHistory1 = new ArticleHistory();
        articleHistory1.setId(1L);
        ArticleHistory articleHistory2 = new ArticleHistory();
        articleHistory2.setId(articleHistory1.getId());
        assertThat(articleHistory1).isEqualTo(articleHistory2);
        articleHistory2.setId(2L);
        assertThat(articleHistory1).isNotEqualTo(articleHistory2);
        articleHistory1.setId(null);
        assertThat(articleHistory1).isNotEqualTo(articleHistory2);
    }
}
