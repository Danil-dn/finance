package fin.kda.project;

import fin.kda.project.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> methodArgumentNotValidException(final MethodArgumentNotValidException exception) {
		return new ResponseEntity<>(
				new ErrorDto(
						"Validation error",
						exception.getBindingResult().getFieldErrors().stream()
								.map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
								.collect(Collectors.toList())
				),
				HttpStatus.BAD_REQUEST
		);
	}

}
