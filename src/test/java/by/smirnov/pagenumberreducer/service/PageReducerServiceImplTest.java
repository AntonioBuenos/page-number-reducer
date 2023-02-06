package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.exception.BadRequestException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PageReducerServiceImplTest {

    private static PageReducerService service;

    @BeforeAll
    static void init() {
        service = new PageReducerServiceImpl();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,4,5,7,8,11,13,12,14,1001,1002,1004,1004,1005,1006:1,2,4,5,7,8,11-14,1001,1002,1004-1006",
            "1,2,3,4,5,7,7,9:1-5,7,9",
            "1,3,32,5,11,7,6,19,2,21,4,8,22,23:1-8,11,19,21-23,32",
            "1, 3, 32, 5, 11, 7, 6, 19, 2, 21, 4 ,8, 22, 23:1-8,11,19,21-23,32",
            "1,2,3,4,5,6,7,8,9,10,11,12:1-12",
            "4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4:4",
            "0,5,6,7,46,73,456,34,35,37,2345,36,0:5-7,34-37,46,73,456,2345",
            "1, 3, 32, 5, 11, 7,, 6, 19, 2, 21, 4 ,8, 22, 23:1-8,11,19,21-23,32"
    },
            delimiter = ':')
    void reducerTest(String input, String expected) {

        assertEquals(expected, service.reduce(input).getReduced());
    }

    @ParameterizedTest
    @NullSource
    void reduceNullTest(String input) {
        assertThrows(BadRequestException.class, () -> service.reduce(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "1,2,3,4,123456789011",
            "jhghfd,sdfjj,1,2,3,4",
            "1,2,-3,4,-5,7,7,9",
            "1-8, 32, 11, 19, 21, 22, 23"
    })
    void reduceThrowsExceptionsTest(String input) {
        assertThrows(BadRequestException.class, () -> service.reduce(input));
    }

}
