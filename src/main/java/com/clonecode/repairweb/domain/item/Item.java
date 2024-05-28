package com.clonecode.repairweb.domain.item;

import com.clonecode.repairweb.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private String serialNumber;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


}
