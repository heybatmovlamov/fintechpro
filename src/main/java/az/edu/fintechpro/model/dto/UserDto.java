package az.edu.fintechpro.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "ID cannot be null")
    long id;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    String name;

    @NotBlank(message = "Surname is required")
    @Size(max = 50, message = "Surname cannot exceed 50 characters")
    String surname;

    @NotBlank(message = "FIN code is required")
    @Size(min = 10, max = 10, message = "FIN code must be exactly 10 characters")
    String finCode;

    @Min(value = 18, message = "Age must be at least 18")
    int age;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number format")
    String phoneNumber;

    @Size(max = 255, message = "Address cannot exceed 255 characters")
    String address;

    @NotBlank
    String password;
//    @NotBlank(message = "Status is required")
//    String status;
}
