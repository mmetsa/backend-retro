package com.ridango.retro.retro.dto;

import lombok.Data;

import java.util.List;

@Data
public class RetroBoardRequest {

    private String name;
    private String teamName;

    private List<String> columns;

}
