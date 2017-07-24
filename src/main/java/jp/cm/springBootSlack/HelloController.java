package jp.cm.springBootSlack;

import jp.cm.springBootSlack.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author oraisaori on 2017/07/08.
 */
@Controller
@EnableAutoConfiguration
@ComponentScan
public class HelloController {

    @Autowired
    private HelloWorldService helloWorldService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return helloWorldService.getHelloMessage();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }
}
