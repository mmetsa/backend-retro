package com.ridango.retro.retro.dto;

import lombok.Data;

@Data
public class BoardColumnItemRequest {

    private Long boardId;
    private Long columnId;
    private String author;
    private String value;

}
