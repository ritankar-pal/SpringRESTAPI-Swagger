package in.ineuron.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.ineuron.errorDetails.ErrorDetails;
import in.ineuron.exception.TouristNotFoundException;

@RestControllerAdvice
public class TouristErrorControllerAdvice {
	
	@ExceptionHandler(TouristNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTouristNotFound(TouristNotFoundException tf){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), tf.getMessage(), "404-Tourist Not Found");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleAllProblems(Exception e){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), e.getMessage(), "Problem In Execution");
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
