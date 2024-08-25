package az.edu.fintechpro.dao.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


import java.util.List;

@Data
@Entity(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor

public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 3470424543096822461L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "fin_code")
    String finCode;

    @Column(name = "age")
    int age;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<AccountEntity> accounts;

}
