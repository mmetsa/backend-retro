package com.ridango.retro.retro.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RetroBoardRequest {

    private String name;
    private LocalDate expirationDate;
    private String teamName;

    private List<String> columns;

}
