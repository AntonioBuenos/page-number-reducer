package by.smirnov.pagenumberreducer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.UUID;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(Exception e) {
        return new ResponseEntity<>(getErrorContainer(e), HttpStatus.BAD_REQUEST);
    }

    private ErrorContainer getErrorContainer(Exception e){
        return ErrorContainer
                .builder()
                .exceptionId(UUID.randomUUID().toString())
                .errorMessage(e.getMessage())
                .e(e.getClass().toString())
                .time(LocalDateTime.now().toString())
                .build();
    }
}
