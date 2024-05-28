package com.clonecode.repairweb.domain.login;

import com.clonecode.repairweb.domain.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Admin implements User{

    @Id @GeneratedValue
    @Column(name = "admin_id")
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

}
