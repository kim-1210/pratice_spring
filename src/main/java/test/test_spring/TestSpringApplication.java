package test.test_spring; //여기서 부터 하위 패키지만 spring에 들어감

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 안에 component scan가 있으며, 이것이 작업 파일들을 찾아줌
public class TestSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSpringApplication.class, args);
	}

}
// tomcat이라는 웹서버가 내장되어있음

// spring bean(Spring container)을 등록하는 방법 => singleton이다.
// Component Scan (Component Spring) : Controller, Service, Repository, Autowired 등 태그가 Component에 속성된다.
// java code self : SpringConfig.java에 있음
// ☞ 예) 임시 storage에서 DB로 변경시 한번에 변경이 가능하도록 해줌 (java code self)