package com.vadim.budgettracker.entity;

import com.vadim.budgettracker.entity.enums.Currency;
import com.vadim.budgettracker.entity.enums.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "operations", schema = "public")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "section")
    private Section section;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "color")
    private String color;

    @Column(name = "logo")
    private String logo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
