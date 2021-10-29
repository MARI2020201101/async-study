package com.mariworld.async;

import netscape.javascript.JSObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;

@SpringBootTest
class AsyncStudyApplicationTests {

	private static final RestTemplate restTemplate = new RestTemplate();
	public static final String URL = "http://localhost:8080/rest/{hello}/v2/{spring}";
	/*@BeforeAll
	public static void getRestTemplate(){
		final RestTemplate restTemplate = new RestTemplate();
	}*/

	@Test
	public void stringObjectTest() throws Exception {

		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			String url = "http://localhost:8080/rest";
			url+="/hello";
			url+="/v2";
			url+="/spring";
			Object result = restTemplate.getForObject(url, Object.class);
			System.out.println(result);
		}
		long end = System.nanoTime();
		System.out.println("==================\n" +(end-start));
	}

	@Test
	public void stringReplaceObjectTest() throws Exception {
		long start = System.nanoTime();
		for (int i = 0; i < 100; i++) {
			URL.replace("{hello}","hello");
			URL.replace("{spring}","spring");
			Object result = restTemplate.getForObject(URI.create(URL), Object.class);
			System.out.println(result);
		}
		long end = System.nanoTime();
		System.out.println("==================\n" +(end-start));
	}
}
