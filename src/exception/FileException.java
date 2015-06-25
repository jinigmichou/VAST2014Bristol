package exception;

public class FileException extends Exception {

    private static final long serialVersionUID = 1L;

    public FileException() {
        super();
    }

    public FileException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public FileException(String arg0) {
        super(arg0);
    }

    public FileException(Throwable arg0) {
        super(arg0);
    }
}



