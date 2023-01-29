package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.exception.BadRequestException;
import by.smirnov.pagenumberreducer.response.ReducerResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_BAD_REQUEST;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_NEGATIVE;
import static by.smirnov.pagenumberreducer.exception.BadRequestException.ERROR_NULL;

@Service
public class PageReducerServiceImpl implements PageReducerService {

    public static final String DELIMITER = ",";
    public static final String REDUCE_FORMAT = "%d-%d";

    @Override
    public ReducerResponse reduce(String numbers) {
        List<Integer> sorted = sort(numbers);
        if(sorted.get(0)<0) throw new BadRequestException(ERROR_NEGATIVE);
        StringJoiner joiner = new StringJoiner(DELIMITER);
        LinkedList<Integer> buffer = new LinkedList<>();
        for (Integer number : sorted) {
            if (buffer.isEmpty() || isNextInRange(buffer.getLast(), number)) {
                buffer.add(number);
            } else {
                joiner.add(checkAndFormat(buffer));
                buffer = new LinkedList<>();
                buffer.add(number);
            }
        }
        joiner.add(checkAndFormat(buffer));

        return new ReducerResponse(numbers, joiner.toString());
    }

    private List<Integer> sort(String numbers) {
        List<Integer> sorted = null;
        try{
            sorted = Arrays
                    .stream(numbers.split(DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .sorted()
                    .distinct()
                    .collect(Collectors.toList());
        } catch (NullPointerException e){
            throw new BadRequestException(ERROR_NULL);
        } catch (NumberFormatException e){
            throw new BadRequestException(ERROR_BAD_REQUEST);
        }return sorted;
    }

    private boolean isNextInRange(Integer previous, Integer number) {
        return previous == number - 1;
    }

    private String checkAndFormat(LinkedList<Integer> buffer) {
        StringJoiner formatted = new StringJoiner(DELIMITER);
        if (buffer.size() > 2) {
            formatted.add(
                    String.format(
                            REDUCE_FORMAT, buffer.getFirst(), buffer.getLast()
                    ));
        } else {
            for (Integer integer : buffer) {
                formatted.add(String.valueOf(integer));
            }
        }
        return formatted.toString();
    }
}
