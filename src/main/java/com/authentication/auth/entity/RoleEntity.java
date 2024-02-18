package com.authentication.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@Data
@Entity
@Table(name = "role")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity  implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;
    private String authority;

    public RoleEntity(int i, String user) {
    }


    @Override
    public String getAuthority() {
        return this.authority;
    }
}
