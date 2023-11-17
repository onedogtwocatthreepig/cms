package com.briup.core;

import com.briup.core.bean.Slideshow;
import com.briup.core.dao.SlideshowDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CmsCoreApplicationTests {
	@Autowired
	private SlideshowDao slideshowDao;

	@Test
	void contextLoads() {
		Slideshow slideshow = slideshowDao.queryById(1);
		System.out.println("slideshow = " + slideshow);
	}

}
