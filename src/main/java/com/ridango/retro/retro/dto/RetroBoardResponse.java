package com.ridango.retro.retro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class RetroBoardResponse {

    private Long id;
    private String name;
    private String teamName;
    private LocalDate expirationDate;
    private boolean isActive;
    private List<BoardColumnResponse> columns;


}
