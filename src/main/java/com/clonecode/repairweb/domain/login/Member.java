package com.clonecode.repairweb.domain.login;

import com.clonecode.repairweb.domain.Address;
import com.clonecode.repairweb.domain.Repair;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member implements User{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

    private String phoneNumber;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Repair> repairs = new ArrayList<>();

    private String role = "MEMBER";
}
