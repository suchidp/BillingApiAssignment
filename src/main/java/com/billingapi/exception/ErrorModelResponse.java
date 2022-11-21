package com.billingapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModelResponse {
    private List<ValidationsError> errorMessage = new ArrayList<>();
}
