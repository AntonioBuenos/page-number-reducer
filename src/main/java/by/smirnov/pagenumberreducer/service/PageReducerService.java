package by.smirnov.pagenumberreducer.service;

import by.smirnov.pagenumberreducer.response.ReducerResponse;

public interface PageReducerService {

    ReducerResponse reduce(String numbers);
}
