package cn.kerninventor.tools.spring.beanvalidationcatcher.beanverifiable.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kern
 * @date 2020/3/17 19:35
 * @description TODO
 */
@RequestMapping("/test/1")
@RestController
public class TestController {

    @PostMapping("")
    public Object test(@RequestBody Test test){
        return test.getMessage();
    }
}
