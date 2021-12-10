package com.application.fluxserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    private Integer id;
    private String login;
    private String password;
}
