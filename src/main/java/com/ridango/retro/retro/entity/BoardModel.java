package com.ridango.retro.retro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class BoardModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String teamName;
    private Date expirationDate;
    private boolean isActive = true;

    @OneToMany(mappedBy = "boardModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardColumnModel> columns;
}
