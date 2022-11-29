package com.billingapi.exception;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorModelResponse {

    private List<ExceptionResponse> errors = new ArrayList<>();
}
