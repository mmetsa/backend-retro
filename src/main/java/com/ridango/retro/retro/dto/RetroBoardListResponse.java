package com.ridango.retro.retro.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RetroBoardListResponse {

    private Long id;
    private String name;
    private String teamName;
    private LocalDate retroDate;

}
