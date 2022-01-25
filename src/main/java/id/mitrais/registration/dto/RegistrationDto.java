package id.mitrais.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author gpk
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private Long id;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String email;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String updatedAt;
}
