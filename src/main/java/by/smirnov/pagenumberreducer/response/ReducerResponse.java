package by.smirnov.pagenumberreducer.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReducerResponse {

    private String original;
    private String reduced;
}
