package com.example.itemvalidator.response;

import com.example.itemvalidator.common.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private ErrorCodes.ResponseCode errorId;
    private String errorMessage;
}
