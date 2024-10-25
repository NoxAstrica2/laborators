package labs.lab6.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(){
        super();
    }

    public EntityNotFoundException(Throwable throwable){
        super(throwable);
    }

    public EntityNotFoundException(String message){
        super(message);
    }
}
