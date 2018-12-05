package com.calculator.tdd;

import com.calculator.tdd.exceptions.CalculatorException;

import java.util.List;

public class Calculator {

    private ClientData clientData;

    public int add(String number) throws CalculatorException {
        clientData = new ClientData(number);
        List<Integer> numbers = clientData.getNumbers();
        List<Integer> negativeNumbers = clientData.findNegativeNumbers(numbers);
        if (negativeNumbers.size() > 0) {
            throw new CalculatorException(String.format("negatives not allowed %s", negativeNumbers.toString()));
        }
        return numbers
                .stream()
                .filter(num -> num <= 1000)
                .mapToInt(num -> num)
                .sum();
    }

}
