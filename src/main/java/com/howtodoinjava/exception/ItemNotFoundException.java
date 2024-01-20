package com.howtodoinjava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends  RuntimeException {
    private Integer id;

    public ItemNotFoundException(Integer id) {
        super("Could not find item " + id);
    }
}

