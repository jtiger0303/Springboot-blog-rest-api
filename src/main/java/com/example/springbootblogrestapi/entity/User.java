package com.example.springbootblogrestapi.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL) //user를 가져올 때 role도 같이
    @JoinTable(name="user_roles",
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
