package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class ArticleTopicTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ArticleTopic.class);
        ArticleTopic articleTopic1 = new ArticleTopic();
        articleTopic1.setId(1L);
        ArticleTopic articleTopic2 = new ArticleTopic();
        articleTopic2.setId(articleTopic1.getId());
        assertThat(articleTopic1).isEqualTo(articleTopic2);
        articleTopic2.setId(2L);
        assertThat(articleTopic1).isNotEqualTo(articleTopic2);
        articleTopic1.setId(null);
        assertThat(articleTopic1).isNotEqualTo(articleTopic2);
    }
}
