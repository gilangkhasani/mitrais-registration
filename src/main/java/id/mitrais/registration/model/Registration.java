package id.mitrais.registration.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author gpk
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "registration")
public class Registration extends AbstractAuditableEntity<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name="mobile_number", unique = true)
    @NotEmpty(message = "Mobile Number may not be empty")
    @Pattern(regexp = "(\\+62|0)[0-9]{11}")
    @NotNull(message = "Mobile Number may not be null")
    private String mobileNumber;

    @Column(name="first_name")
    @NotEmpty(message = "First Name may not be empty")
    @NotNull(message = "First Name may not be null")
    private String firstName;

    @Column(name="last_name")
    @NotEmpty(message = "Last Name may not be empty")
    @NotNull(message = "Last Name may not be null")
    private String lastName;

    @Column(name="dob")
    private LocalDate dob;

    @Column(name="gender")
    private String gender;

    @Column(name="email", unique = true)
    @NotEmpty(message = "Email may not be empty")
    @Email
    @NotNull(message = "Email may not be null")
    private String email;

}
