package dev.camilo.customdeserializer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTest
class CustomDeserializerApplicationTests {

	@Value("classpath:data/blog-post.json")
	Resource blogPostJson;

	@Autowired
	ObjectMapper objectMapper;

	String json;

	@Test
	void contextLoads() {
		assertNotNull(objectMapper);
	}

	@BeforeEach
	void setUp() throws IOException {
		json = new String(Files.readAllBytes(blogPostJson.getFile().toPath()));
		var blogPostWrapper = this.objectMapper.readValue(json, BlogPostWrapper.class);
		System.out.println(blogPostWrapper);
	}

}
