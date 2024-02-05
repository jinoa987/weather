package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherApplicationTests {

	@Test
	void equalTest() {
		assertEquals(1, 1);
	}

	@Test
	void nullTest() {
		assertNull(null);
	}

	@Test
	void trueTest(){
		assertTrue(1==1);
	}
}
