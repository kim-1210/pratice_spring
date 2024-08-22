package test.test_spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    
    @GetMapping("test")
    public String test(Model model){
        model.addAttribute("data", "test!!");
        return "test"; //test.html을 찾음
    }

    @GetMapping("test-mvc")
    public String testMVC(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "test-tem";
    }

    @GetMapping("test-string")
    @ResponseBody //html의 부분을 template에서 가져오는 것이 아닌 java에서 그대로 사용
    //http응답에 바로전달 함
    //HttpMessageConverter가 동작함
    public String testString(@RequestParam("name") String name){
        return "test " + name;
    }

    @GetMapping("test-api")
    @ResponseBody
    public test testapi(@RequestParam("name") String name){ //api : 데이터만 전송하기 <json>
        test TEST = new test();
        TEST.setName(name);
        return TEST;
    }

    static class test {
        private String name;
        public String getName(){
            return this.name;
        }
        public void setName(String name){
            this.name = name;
        }
    }
}
