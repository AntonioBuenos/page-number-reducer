package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static by.smirnov.pagenumberreducer.constants.Constants.DELIMITER;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_BAD_REQUEST;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_EMPTY;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_NEGATIVE;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_NULL;

@Service
public class SorterServiceImpl implements SorterService{

    public LinkedList<Integer> sort(String numbers) {
        LinkedList<Integer> sorted;
        try {
            sorted = Arrays
                    .stream(numbers.split(DELIMITER))
                    .map(String::trim)
                    .filter(Predicate.not(String::isEmpty))
                    .map(Integer::parseInt)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (NullPointerException e) {
            throw new BadRequestException(ERROR_NULL);
        } catch (NumberFormatException e) {
            throw new BadRequestException(ERROR_BAD_REQUEST);
        }
        if (sorted.isEmpty()) throw new BadRequestException(ERROR_EMPTY);
        else if (sorted.getFirst() < 0) throw new BadRequestException(ERROR_NEGATIVE);
        else if (sorted.getFirst() == 0) sorted.removeFirst();
        return sorted;
    }
}
