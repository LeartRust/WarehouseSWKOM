package at.fhtw.swen3.services;


import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.persistence.repositories.ErrorRepository;
import lombok.Getter;


@Getter
public class BLException extends Exception {

    private  Exception innerException;

    private ErrorEntity errorEntity;


    public BLException(int errorId, String errorMsg, Exception e) {
        this.innerException=e;
        this.errorEntity=ErrorEntity.builder().errorMessage(errorMsg).id(errorId).build();
    }

    @Override
    public String getMessage() {
        return errorEntity.getErrorMessage();
    }


    /*
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request){
        String bodyOfResponse = "this should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
     */
}
