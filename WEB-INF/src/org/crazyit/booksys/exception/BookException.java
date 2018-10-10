package org.crazyit.booksys.exception;

public class BookException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BookException(){}

    public BookException(String msg) {
        super(msg);
    }
}
