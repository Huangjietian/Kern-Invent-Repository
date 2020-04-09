package cn.kerninventor.tools.spring.beanvalidationcatcher.paramtervalidate;

import cn.kerninventor.tools.spring.beanvalidationcatcher.beanverifiable.demo.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kern
 * @date 2020/4/8 9:28
 * @description TODO
 */
@RestController
@RequestMapping("/test")
public class Testcontroller {

    @PostMapping("")
    public String test(Test test) {
        return "success!";
    }
}
