package com.neoSoft.User.Management.Entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE user SET is_active=1 WHERE user_id=?")
@Where(clause = "is_active = 0")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @NonNull
    @Column(length = 20)
    private String firstName;

    @NonNull
    @Column(length = 20)
    private String lastName;

    @NotNull
    private String dob;

    @Column(length = 10, nullable = false)
    private String mobileNo;

    @Email
    @Column(unique = true)
    private String emailId;

    @NotBlank
    private String password;

    @NotNull
    private LocalDate joiningDate;

    @Column(length = 200, nullable = false)
    private String address;

    @NotNull
    @Column(length = 10)
    private String pinCode;

    private boolean isActive = Boolean.FALSE;
}
