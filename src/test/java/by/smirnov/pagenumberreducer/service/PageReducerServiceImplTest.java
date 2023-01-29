package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.exception.BadRequestException;
import by.smirnov.pagenumberreducer.response.ReducerResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PageReducerServiceImplTest {

    private static PageReducerService service;

    @BeforeAll
    static void init() {
        service = new PageReducerServiceImpl();
    }

    @Test
    void reducerTest() {

        String input00 = "1,2,4,5,7,8,11,13,12,14,1001,1002,1004,1004,1005,1006";
        ReducerResponse expected00 = new ReducerResponse(input00, "1,2,4,5,7,8,11-14,1001,1002,1004-1006");

        String input01 = "1,2,3,4,5,7,7,9";
        ReducerResponse expected01 = new ReducerResponse(input01, "1-5,7,9");

        String input02 = "1,3,32,5,11,7,6,19,2,21,4,8,22,23";
        ReducerResponse expected02 = new ReducerResponse(input02, "1-8,11,19,21-23,32");

        String input03 = "1, 3, 32, 5, 11, 7, 6, 19, 2, 21, 4 ,8, 22, 23";
        ReducerResponse expected03 = new ReducerResponse(input03, "1-8,11,19,21-23,32");

        String input04 = "1,2,3,4,5,6,7,8,9,10,11,12";
        ReducerResponse expected04 = new ReducerResponse(input04, "1-12");

        String input05 = "4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4";
        ReducerResponse expected05 = new ReducerResponse(input05, "4");

        String input06 = "0,5,6,7,46,73,456,34,35,37,2345,36,0";
        ReducerResponse expected06 = new ReducerResponse(input06, "5-7,34-37,46,73,456,2345");

        String input07 = "1, 3, 32, 5, 11, 7,, 6, 19, 2, 21, 4 ,8, 22, 23";
        ReducerResponse expected07 = new ReducerResponse(input07, "1-8,11,19,21-23,32");

        assertAll("Page number reducer operations check",
                () -> assertEquals(expected00.getReduced(), service.reduce(input00).getReduced()),
                () -> assertEquals(expected01.getReduced(), service.reduce(input01).getReduced()),
                () -> assertEquals(expected02.getReduced(), service.reduce(input02).getReduced()),
                () -> assertEquals(expected03.getReduced(), service.reduce(input03).getReduced()),
                () -> assertEquals(expected04.getReduced(), service.reduce(input04).getReduced()),
                () -> assertEquals(expected05.getReduced(), service.reduce(input05).getReduced()),
                () -> assertEquals(expected06.getReduced(), service.reduce(input06).getReduced()),
                () -> assertEquals(expected07.getReduced(), service.reduce(input07).getReduced())
        );
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
