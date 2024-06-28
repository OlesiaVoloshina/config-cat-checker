package test.configcatchecker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.configcatchecker.cat.CatService;
import test.configcatchecker.cat.ExternalService;

import java.util.List;

@RestController
@RequestMapping("/cat")
public class CatController {


    @Autowired
    private CatService catService;

    @Autowired
    private ExternalService externalService;

    @GetMapping("/start")
    public void startLoadTest() {
        externalService.starter();
    }

    @GetMapping("/single")
    public List<String> testSingleRun()  {
        return catService.executeTestRun();
    }
}
