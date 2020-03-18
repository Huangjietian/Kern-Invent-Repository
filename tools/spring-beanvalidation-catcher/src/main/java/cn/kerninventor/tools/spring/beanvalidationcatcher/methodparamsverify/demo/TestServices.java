package cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify.demo;

import cn.kerninventor.tools.spring.beanvalidationcatcher.beanverifiable.demo.Test;
import cn.kerninventor.tools.spring.beanvalidationcatcher.methodparamsverify.ParamsVerify;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kern
 * @date 2020/3/17 19:35
 * @description TODO
 */
@RequestMapping("/test/2")
@RestController
public class TestServices {

    @ParamsVerify(handler = TestHandler.class)
    @PostMapping("")
    public Object test(@RequestBody Test test, @RequestParam(name = "name") String name){
        return "succeed!";
    }
}
