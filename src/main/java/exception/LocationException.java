package exception;

/**
 * Created by dell on 15.08.2015.
 */
public class LocationException extends Exception {

    public LocationException() {
    }

    public LocationException(String message) {
        super(message);
    }

    public LocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationException(Throwable cause) {
        super(cause);
    }

    public LocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
