package com.vadim.budgettracker.entity;

import com.vadim.budgettracker.entity.enums.Currency;
import com.vadim.budgettracker.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "confirmed")
    private Boolean confirmed;

    @Column(name = "language")
    private String language;

    @Column(name = "currency")
    private String currency;

    @Column(name = "mode")
    private String mode;

    @OneToMany(mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Category> categories;

    @OneToMany(mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Operation> operations;
}
