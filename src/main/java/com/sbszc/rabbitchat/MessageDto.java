package com.sbszc.rabbitchat;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class MessageDto {
    @NotBlank
    private String message;
    @NotBlank
    private String destination;
    @NotBlank
    private String destinationType;

    private Map<String, Object> messageDetails = new LinkedHashMap<>();
}
