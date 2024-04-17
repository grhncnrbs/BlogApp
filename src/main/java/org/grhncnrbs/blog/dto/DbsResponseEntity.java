package org.grhncnrbs.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DbsResponseEntity<T>{

    T data;
    private String message;

}
