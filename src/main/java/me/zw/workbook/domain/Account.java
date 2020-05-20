package me.zw.workbook.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "ACCOUNT_NAME", unique = true)
    private String accountName;

    @NotNull
    private String password;

    private Boolean enabled = true;
}
