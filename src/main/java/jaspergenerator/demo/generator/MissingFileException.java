package jaspergenerator.demo.generator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing File")
@NoArgsConstructor
public class MissingFileException extends RuntimeException {

    @Getter
    @Setter
    private String message = "Invalid Status";

    public MissingFileException(String message) {
        super();
        this.message = message;
    }

}
