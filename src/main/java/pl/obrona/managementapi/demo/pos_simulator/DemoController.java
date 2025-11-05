package pl.obrona.managementapi.demo.pos_simulator;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/demo")
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/employees")
    public List<Long> getEmployeeIds() {
        return demoService.getEmployeeIds();
    }

    @GetMapping("/products")
    public List<Long> getProductIds() {
        return demoService.getProductIds();
    }

}
