package com.ridango.retro.retro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BoardItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardColumnModel boardColumnModel;

    private Integer upVotes;
    private Integer downVotes;

}
