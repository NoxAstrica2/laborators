package labs.lab5.exception;

public class UniqnessException extends RuntimeException {

    public UniqnessException(){
        super();
    }

    public UniqnessException(Throwable throwable){
        super(throwable);
    }

    public UniqnessException(String message){
        super(message);
    }
}
