package in.scalive.votezy.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse>handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		ErrorResponse er=new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
		return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(VoteNotAllowedException.class)
	public ResponseEntity<ErrorResponse>handleResourceNotFoundException(VoteNotAllowedException ex)
	{
		ErrorResponse er=new ErrorResponse(HttpStatus.FORBIDDEN.value(),ex.getMessage());
		return new ResponseEntity<>(er,HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ErrorResponse>handleResourceNotFoundException(DuplicateResourceException ex)
	{
		ErrorResponse er=new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
		return new ResponseEntity<>(er,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>handleResourceNotFoundException(MethodArgumentNotValidException ex)
	{
		Map<String,String>errors=new HashMap<>();
		BindingResult bres=ex.getBindingResult();
		List<FieldError>errorList= bres.getFieldErrors();
		for(FieldError e:errorList)
		{
			errors.put(e.getField(),e.getDefaultMessage());
		}
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse>handleResourceNotFoundException(Exception ex)
	{
		ErrorResponse er=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getMessage());
		return new ResponseEntity<>(er,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
