package az.edu.fintechpro.dao.entity;

import az.edu.fintechpro.model.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity(name = "Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true, length = 16)
    private String accountNumber;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "pin", length = 4)
    private String pin;

    @Column(name = "cvv", length = 3)
    private String cvv;

    @ManyToOne
    @JoinColumn(name = "user_id") // Replace 'user_id' with the actual column name in your DB schema
    private UserEntity user;

}
