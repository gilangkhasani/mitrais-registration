package id.mitrais.registration.controller;


import id.mitrais.registration.dto.RegistrationDto;
import id.mitrais.registration.service.RegistrationService;
import id.mitrais.registration.util.ApiResponse;
import org.springframework.web.bind.annotation.*;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

/**
 *
 * @author gpk
 */
@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping()
    public ResponseEntity<List<RegistrationDto>> list() {
        List<RegistrationDto> special = registrationService.getAllData();
        return new ResponseEntity(new ApiResponse<>("000","success",special),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        RegistrationDto special = registrationService.getById(id);
        return new ResponseEntity(new ApiResponse<RegistrationDto>("000","success",special),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody RegistrationDto input) {
        try {
            RegistrationDto special = registrationService.create(input);
            return new ResponseEntity(new ApiResponse<RegistrationDto>("000","success",special),HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage(), e);
        }

    }

}
