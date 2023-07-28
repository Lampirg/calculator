package com.lampirg.calculator.web;

import com.lampirg.calculator.logic.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calc")
@RequiredArgsConstructor
public class CalculatorController {

    private final Calculator<String, String> calculator;

    @GetMapping("/{expression}")
    public String calculateWithoutBody(@PathVariable String expression) {
        String toCalculate = expression.replaceAll(" ", "");
        return calculator.calculate(toCalculate);
    }

    @PostMapping
    public String calculate(@RequestBody Request request) {
        String toCalculate = request.expression().replaceAll(" ", "");
        return calculator.calculate(toCalculate);
    }

    private record Request(String expression) {}
}
