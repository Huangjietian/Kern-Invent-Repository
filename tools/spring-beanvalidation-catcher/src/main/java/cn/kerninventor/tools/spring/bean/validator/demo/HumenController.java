package cn.kerninventor.tools.spring.bean.validator.demo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kern
 * @date 2020/5/11 19:57
 * @description
 */
@RestController
@RequestMapping("/humen")
public class HumenController {

    @PostMapping("")
    public void addHumen(@RequestBody Humen humen) {
        System.out.println("添加一个人员" + System.lineSeparator() + humen.toString());
    }
}
