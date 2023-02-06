package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.response.ReducerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.StringJoiner;

import static by.smirnov.pagenumberreducer.constants.Constants.DELIMITER;
import static by.smirnov.pagenumberreducer.constants.Constants.REDUCE_FORMAT;

@Service
@RequiredArgsConstructor
public class PageReducerServiceImpl implements PageReducerService {

    private final SorterService sorterService;

    @Override
    public ReducerResponse reduce(String numbers) {
/*        LinkedList<Integer> sorted = sorterService.sort(numbers);
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

        return new ReducerResponse(numbers, joiner.toString());*/

        Integer[] sorted = sorterService.sort(numbers);
        StringJoiner joiner = new StringJoiner(DELIMITER);

        int curr = 0, next = 0;
        while (curr < sorted.length) {
            while (++next < sorted.length && sorted[next] - sorted[next - 1] == 1);
            if (next - curr > 2) {
                joiner.add(String.format(REDUCE_FORMAT, sorted[curr], sorted[next - 1]));
                curr = next;
            } else {
                for (; curr < next; curr++)
                    joiner.add(String.valueOf(sorted[curr]));
            }
        }
        return new ReducerResponse(numbers, joiner.toString());
    }

/*    private boolean isNextInRange(Integer previous, Integer number) {
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
    }*/
}
