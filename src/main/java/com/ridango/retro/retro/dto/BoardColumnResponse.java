package com.ridango.retro.retro.dto;

import lombok.Data;

import java.util.List;

@Data
public class BoardColumnResponse {

    private Long id;
    private String name;
    private List<BoardItemResponse> items;

}
