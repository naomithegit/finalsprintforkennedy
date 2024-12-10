package com.keyin.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tree_entity")
public class TreeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "input_numbers", nullable = false)
    private String inputNumbers;

    @Column(name = "tree_structure", columnDefinition = "TEXT", nullable = false)
    private String treeStructure;

    public TreeEntity() {}

    public TreeEntity(String inputNumbers, String treeStructure) {
        this.inputNumbers = inputNumbers;
        this.treeStructure = treeStructure;
    }

    public Long getId() {
        return id;
    }

    public String getInputNumbers() {
        return inputNumbers;
    }

    public String getTreeStructure() {
        return treeStructure;
    }
}
