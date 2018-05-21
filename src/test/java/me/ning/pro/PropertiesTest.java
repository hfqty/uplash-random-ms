package me.ning.pro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {
	
	 @Value("${image.save.path}")
	private String imgSavePath;
	
	@Test
	public void testConfig() {
		System.out.println(imgSavePath);
	}

}
