package exception;

/**
 * Created by dell on 21.08.2015.
 */
public class UrlException extends Exception {

    public UrlException() {
    }

    public UrlException(String message) {
        super(message);
    }

    public UrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public UrlException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UrlException(Throwable cause) {
        super(cause);
    }
}
