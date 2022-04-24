package com.ridango.retro.retro.dto;

import lombok.Data;

@Data
public class BoardItemResponse {

    private Long id;
    private String value;
    private String author;
    private Integer upVotes;
    private Integer downVotes;

}
