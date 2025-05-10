package com.stal0.planejaPlus.entities;

import com.stal0.planejaPlus.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
