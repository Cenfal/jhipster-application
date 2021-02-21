package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleTagTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleTag.class);
        ArticleTag articleTag1 = new ArticleTag();
        articleTag1.setId(1L);
        ArticleTag articleTag2 = new ArticleTag();
        articleTag2.setId(articleTag1.getId());
        assertThat(articleTag1).isEqualTo(articleTag2);
        articleTag2.setId(2L);
        assertThat(articleTag1).isNotEqualTo(articleTag2);
        articleTag1.setId(null);
        assertThat(articleTag1).isNotEqualTo(articleTag2);
    }
}
