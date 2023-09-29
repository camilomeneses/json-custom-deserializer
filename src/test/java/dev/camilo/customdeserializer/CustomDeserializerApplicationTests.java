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

import com.fasterxml.jackson.databind.JsonNode;
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

		// Classes way
		/* var blogPostWrapper = objectMapper.readValue(json, BlogPostWrapper.class); 
		 * 
		 * System.out.println(blogPostWrapper.getData().getAllPost().getEdges());
		*/

		// JsonNode way
		JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
		// JsonNode data first level
		/* System.out.println(jsonNode.get("data")); */

		// JsonNode format
		/* System.out.println(jsonNode.toPrettyString()); */

		// JsonNode data access by get method
		/* jsonNode.get("data")
			.get("allPost")
			.get("edges")
			.forEach(System.out::println); */

		// Custom Deserializer way
		var blog = objectMapper.readValue(json, Blog.class);
		blog.post().forEach(System.out::println);
	}

}
