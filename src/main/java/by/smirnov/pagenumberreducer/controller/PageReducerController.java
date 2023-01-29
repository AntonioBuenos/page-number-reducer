package by.smirnov.pagenumberreducer.controller;

import by.smirnov.pagenumberreducer.exception.ErrorContainer;
import by.smirnov.pagenumberreducer.response.ReducerResponse;
import by.smirnov.pagenumberreducer.service.PageReducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reducedPageNumbers")
@Tag(
        name = "Page number reducer controller",
        description = "This controller is responsible for the reduction of a given range of page numbers for printer"
)
public class PageReducerController {

    private final PageReducerService service;

    @Operation(
            method = "GET",
            summary = "Finding a user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful Request"),
                    @ApiResponse(responseCode = "400", description = "Bad Request. " +
                            "All page numbers must be not negative integers, separated by comas", content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ErrorContainer.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unexpected Internal Server Error", content =
                            @Content)
            },
            description = "This method transforms a list of page numbers in one String line, separated by ',' " +
                    "into ascending reduced format of a String line for printer. " +
                    "E.g.: \"1,3,32,5,11,7,6,19,2,21,4,8,22,23\" -> \"1-8,11,19,21-23,32\". " +
                    "And returns a ReducerResponse object with both initial String input line 'original' " +
                    "and reduced String line 'reduced'"
    )
    @GetMapping
    public ResponseEntity<ReducerResponse> show(String rawPageNumbers) {
        ReducerResponse response = service.reduce(rawPageNumbers);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
