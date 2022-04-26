package com.ridango.retro.retro.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class BoardColumnModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private BoardModel boardModel;

    @OneToMany(mappedBy = "boardColumnModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardItemModel> items;

}
