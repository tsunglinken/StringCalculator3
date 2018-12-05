package com.calculator.tdd;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ClientData {
    private String number;
    private String delimiter;

    public ClientData(String number) {
        this.number = number;
        this.delimiter = getDelimiter(",");
    }

    private String getDelimiter(String delimiter) {
        return String.format("%s|%n", delimiter)
                .replaceAll("(\\]\\[)", "\\|")
                .replaceAll("(\\[|\\])", "")
                .replaceAll("(\\*|\\.|\\?)", "\\\\$1");
    }

    public List<Integer> getNumbers() {
        if(this.number.startsWith("//")) {
            Matcher matcher = Pattern.compile("//(.*)\\s(.*)").matcher(this.number);
            matcher.matches();
            String delimiter = getDelimiter(matcher.group(1));
            String number = matcher.group(2);
            return transform(number.split(delimiter), Integer::parseInt);
        }

        return transform(this.number.split(String.format(",|%n")), Integer::parseInt);
    }

    public List<Integer> findNegativeNumbers(List<Integer> numbers) {
        return numbers
                .stream()
                .filter(num -> num < 0)
                .collect(Collectors.toList());
    }

    private <T, U> List<U> transform(T[] splitNumbers, Function<T, U> parseInt) {
        return Arrays.asList(splitNumbers)
                .stream()
                .filter(num -> num != null && num != "")
                .map(parseInt)
                .collect(Collectors.toList());
    }
}
