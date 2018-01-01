import java.sql.SQLException;

public class CandyRuntimeException extends RuntimeException{
    public CandyRuntimeException(String message){
       super(message);
    }
    public CandyRuntimeException(String message, SQLException exception){
        super(message, exception);
    }

}
