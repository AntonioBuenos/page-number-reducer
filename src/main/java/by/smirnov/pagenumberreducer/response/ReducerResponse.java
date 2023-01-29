package by.smirnov.pagenumberreducer.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class ReducerResponse {

    private String original;
    private String reduced;

}
