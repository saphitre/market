package com.example.market.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "security", name = "users")
@Schema(name = "User", description = "Данные пользователя")
public class User {
    @Schema(description = "Идентификационный номер пользователя (генерируется автоматически)")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Имя пользователя")
    @Column(name="username",unique = true)
    private String username;

    @Schema(description = "Пароль")
    @Column(name = "password")
    private String password;

    @Schema(description = "Список ролей пользователя")
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", schema = "security", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public User(String username, String password,Set<Role> roles){
        this.username=username;
        this.password=password;
        this.roles=roles;
    }
}
