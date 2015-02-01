package husd.wsi.exception;

/**
 * Created by shengdong on 2015/1/17.
 */
public class ShortYException extends RuntimeException {
    public ShortYException() {
    }

    public ShortYException(String message) {
        super(message);
    }

    public ShortYException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShortYException(Throwable cause) {
        super(cause);
    }

    public ShortYException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
