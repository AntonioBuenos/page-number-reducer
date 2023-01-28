package by.smirnov.pagenumberreducer.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PageReducerServiceImplTest {

    private static PageReducerService service;

    @BeforeAll
    static void init() {
        service = new PageReducerServiceImpl();
    }

    @Test
    void reducerTest() {

        String input00 = "1,2,4,5,7,8,11,13,12,14,1001,1002,1004,1004,1005,1006";
        String expected00 = "1,2,4,5,7,8,11-14,1001,1002,1004-1006";

        String input01 = "1,2,3,4,5,7,7,9";
        String expected01 = "1-5,7,9";

        String input02 = "1,3,32,5,11,7,6,19,2,21,4,8,22,23";
        String expected02 = "1-8,11,19,21-23,32";

        String input03 = "1, 3, 32, 5, 11, 7, 6, 19, 2, 21, 4 ,8, 22, 23";
        String expected03 = "1-8,11,19,21-23,32";

        String input04 = "1,2,3,4,5,6,7,8,9,10,11,12";
        String expected04 = "1-12";

        String input05 = "4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4";
        String expected05 = "4";

        String input06 = "0,5,6,7,46,73,456,34,35,37,2345,36,0";
        String expected06 = "5-7,34-37,46,73,456,2345";

        String input07 = "1, 3, 32, 5, 11, 7,, 6, 19, 2, 21, 4 ,8, 22, 23";
        String expected07 = "1-8,11,19,21-23,32";

        String input08 = "1-8, 32, 11, 19, 21, 22, 23";
        String expected08 = "1-8,11,19,21-23,32";

        assertAll("Page number reducer operations check",
                () -> assertEquals(expected00, service.reduce(input00)),
                () -> assertEquals(expected01, service.reduce(input01)),
                () -> assertEquals(expected02, service.reduce(input02)),
                () -> assertEquals(expected03, service.reduce(input03)),
                () -> assertEquals(expected04, service.reduce(input04)),
                () -> assertEquals(expected05, service.reduce(input05)),
                () -> assertEquals(expected06, service.reduce(input06)),
                () -> assertEquals(expected07, service.reduce(input07)),
                () -> assertEquals(expected08, service.reduce(input08))
        );
    }

    @ParameterizedTest
    @NullSource
    void reduceNullTest(String input){
        assertThrows(NullPointerException.class, () -> service.reduce(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "1,2,3,4,123456789011",  "jhghfd,sdfjj,1,2,3,4", "1,2,-3,4,-5,7,7,9"})
    void reduceThrowsExceptionsTest(String input){
        assertThrows(NumberFormatException.class, () -> service.reduce(input));
    }

}
