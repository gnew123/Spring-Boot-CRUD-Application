package com.spring.blog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends  RuntimeException {
     private String resourceName;
     private String filedName;
     private Long fieldValue;

     public ResourceNotFoundException(String resourceName, String filedName, Long fieldValue) {
          super(String.format("%s not found with %s : %s", resourceName, filedName, fieldValue));
          this.resourceName = resourceName;
          this.filedName = filedName;
          this.fieldValue = fieldValue;
     }
}
