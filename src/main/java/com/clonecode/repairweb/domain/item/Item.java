package com.clonecode.repairweb.domain.item;

import com.clonecode.repairweb.domain.Category;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    @EqualsAndHashCode.Include
    private Long id;

    private String name;

    private String serialNumber;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    private int repairFee;


}
