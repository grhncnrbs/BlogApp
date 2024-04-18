package org.grhncnrbs.blog.exception;

public class RecordNotFoundException extends RuntimeException{

    String message;

    public RecordNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
