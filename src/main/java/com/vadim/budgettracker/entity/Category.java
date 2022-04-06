package com.vadim.budgettracker.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories", schema = "public")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "section")
    private String section;

    @Column(name = "color")
    private String color;

    @Column(name = "logo")
    private String logo;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "categories_operations",
            joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "operation_id", referencedColumnName = "id"))
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @ToString.Exclude
    private List<Operation> operations;

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", color='" + color + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
