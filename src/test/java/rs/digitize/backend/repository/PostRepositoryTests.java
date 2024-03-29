package rs.digitize.backend.repository;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import rs.digitize.backend.entity.Category;
import rs.digitize.backend.entity.Post;
import rs.digitize.backend.entity.Tag;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rs.digitize.backend.util.TestUtils.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class PostRepositoryTests {
	@Autowired
	PostRepository postRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	TagRepository tagRepository;

	@AfterEach
	void teardown() {
		postRepository.deleteAll();
		tagRepository.deleteAll();
		categoryRepository.deleteAll();
	}

	@Test
	void test_savePost(){
		Post post = getPost();
		Category category = getCategory();
		Tag tag = getTag();
		category = categoryRepository.save(category);
		tag = tagRepository.save(tag);
		post.setCategory(category);
		post.setTags(Collections.singletonList(tag));

		post = postRepository.save(post);

		assertEquals(1, postRepository.count());
		assertEquals(post.getCategory(), category);
		assertEquals(tag, post.getTags().get(0));
	}
}
