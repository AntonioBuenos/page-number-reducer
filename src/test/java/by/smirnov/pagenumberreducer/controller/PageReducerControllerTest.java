package by.smirnov.pagenumberreducer.controller;

import by.smirnov.pagenumberreducer.response.ReducerResponse;
import by.smirnov.pagenumberreducer.service.PageReducerService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PageReducerControllerTest {

    @Autowired
    private PageReducerService service;
    @Autowired
    private PageReducerController controller = new PageReducerController(service);

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,4,5,7,8,11,13,12,14,1001,1002,1004,1004,1005,1006:1,2,4,5,7,8,11-14,1001,1002,1004-1006",
            "1,2,3,4,5,7,7,9:1-5,7,9",
            "1,3,32,5,11,7,6,19,2,21,4,8,22,23:1-8,11,19,21-23,32",
            "1, 3, 32, 5, 11, 7, 6, 19, 2, 21, 4 ,8, 22, 23:1-8,11,19,21-23,32",
            "1,2,3,4,5,6,7,8,9,10,11,12:1-12",
            "4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4:4",
            "0,5,6,7,46,73,456,34,35,37,2345,36,0:5-7,34-37,46,73,456,2345",
            "1, 3, 32, 5, 11, 7,, 6, 19, 2, 21, 4 ,8, 22, 23:1-8,11,19,21-23,32",
            "10,8,1,2,-3,4,-5,7,7,9:1,2,4,7-10"
    },
            delimiter = ':')
    void showOkStatusTest(String input, String expected) {

        ResponseEntity<ReducerResponse> response = controller.show(input);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @ParameterizedTest
    @NullSource
    void showNullTest(String input) {
        assertThrows(ConstraintViolationException.class, () -> controller.show(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "jhghfd,sdfjj,1,2,3,4"
    })
    void showThrowsValidationExceptionsTest(String input) {
        assertThrows(ConstraintViolationException.class, () -> controller.show(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,123456789011",
            "1-8, 32, 11, 19, 21, 22, 23"
    })
    void showThrowsNumberFormatExceptionsTest(String input) {
        assertThrows(NumberFormatException.class, () -> controller.show(input));
    }
}
