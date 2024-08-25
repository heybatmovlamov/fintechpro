package az.edu.fintechpro.model.dto;


import az.edu.fintechpro.model.enums.AccountStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    @NotNull(message = "ID cannot be null")
    long id;

    @NotBlank(message = "Account Number is required")
    @Size(min = 16, max = 16, message = "Acoount Number cannot exceed 16 characters")
    String accountNumber;

    @NotBlank(message = "Balance is required")
    @Size(min = 0, max = 100000000)
    BigDecimal balance;

    @NotBlank(message = "Pin code is required")
    @Size(min = 4, max = 4, message = "Pin code must be exactly 4 characters")
    String pin;

    @NotBlank(message = "CVV is required")
    @Size(min = 3, max = 3, message = "CVV  must be exactly 3 characters")
    int cvv;

    @NotBlank(message = "Account status  is required")
    @Enumerated(EnumType.STRING)
    AccountStatus accountStatus;

}
