package com.hoptool.exceptions;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@ToString
@Getter
@Setter
public class AppMessage {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    @Builder.Default
    private String summary = "";
    @Builder.Default
    private String code = "00";
    @Builder.Default
    private String details = "";
    @Builder.Default
    private List<FieldDetail> fields = List.of();
}
