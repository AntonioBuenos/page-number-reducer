package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.function.Predicate;

import static by.smirnov.pagenumberreducer.constants.Constants.DELIMITER;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_BAD_REQUEST;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_EMPTY;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_NULL;

@Service
public class SorterServiceImpl implements SorterService {

    public Integer[] sort(String numbers) {
        Integer[] sorted;
        try {
            sorted = Arrays
                    .stream(numbers.split(DELIMITER))
                    .map(String::trim)
                    .filter(Predicate.not(String::isEmpty))
                    .map(Integer::parseInt)
                    .filter(i -> i > 0)
                    .sorted()
                    .distinct()
                    .toArray(Integer[]::new);
        } catch (NullPointerException e) {
            throw new BadRequestException(ERROR_NULL);
        } catch (NumberFormatException e) {
            throw new BadRequestException(ERROR_BAD_REQUEST);
        }
        if (sorted.length == 0) throw new BadRequestException(ERROR_EMPTY);
        return sorted;
    }
}
