package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.response.ReducerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

import static by.smirnov.pagenumberreducer.constants.Constants.DELIMITER;
import static by.smirnov.pagenumberreducer.constants.Constants.REDUCE_FORMAT;

@Service
@RequiredArgsConstructor
public class PageReducerServiceImpl implements PageReducerService {

    private final SorterService sorterService;

    @Override
    public ReducerResponse reduce(String numbers) {

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
}
