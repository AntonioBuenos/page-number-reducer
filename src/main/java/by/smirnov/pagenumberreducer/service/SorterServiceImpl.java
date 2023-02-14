package by.smirnov.pagenumberreducer.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Arrays;
import java.util.function.Predicate;

import static by.smirnov.pagenumberreducer.constants.Constants.DELIMITER;

@Service
public class SorterServiceImpl implements SorterService {

    public Integer[] sort(String numbers) {
        Integer[] sorted;
        sorted = Arrays
                .stream(numbers.split(DELIMITER))
                .map(String::trim)
                .filter(Predicate.not(String::isEmpty))
                .map(Integer::parseInt)
                .filter(i -> i > 0)
                .sorted()
                .distinct()
                .toArray(Integer[]::new);
        return sorted;
    }
}
