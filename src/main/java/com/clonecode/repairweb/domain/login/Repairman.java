package com.clonecode.repairweb.domain.login;

import com.clonecode.repairweb.domain.Address;
import com.clonecode.repairweb.domain.Repair;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Repairman {

    @Id
    @GeneratedValue
    @Column(name = "repairman_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String phoneNumber;

    private String loginId;

    private String password;

    private String region;

    @OneToMany(mappedBy = "repairman")
    private List<Repair> repairs = new ArrayList<>();
}
