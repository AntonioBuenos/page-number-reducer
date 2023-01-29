package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.response.ReducerResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Service
public class PageReducerServiceImpl implements PageReducerService {

    public static final String DELIMITER = ",";
    public static final String REDUCE_FORMAT = "%d-%d";

    @Override
    public ReducerResponse reduce(String numbers) {
        List<Integer> sorted = sort(numbers);
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
        if (!buffer.isEmpty()) joiner.add(checkAndFormat(buffer));

        return new ReducerResponse(numbers, joiner.toString());
    }

    private List<Integer> sort(String numbers) {
        return Arrays
                .stream(numbers.split(DELIMITER))
                .map(String::trim)
                .map(Integer::parseInt)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
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
