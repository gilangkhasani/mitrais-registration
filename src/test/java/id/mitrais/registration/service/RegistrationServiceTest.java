package id.mitrais.registration.service;

import id.mitrais.registration.dto.RegistrationDto;
import id.mitrais.registration.mapper.RegistrationMapper;
import id.mitrais.registration.model.Registration;
import id.mitrais.registration.repository.RegistrationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @Spy
    private RegistrationMapper registrationMapper = Mappers.getMapper(RegistrationMapper.class);

    @InjectMocks
    @Autowired
    private RegistrationService registrationService;

    @Test
    void shouldSavedSuccessFully() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setFirstName("xxxx");
        registrationDto.setLastName("xxxx");
        registrationDto.setEmail("xxxx@xxxx.com");
        registrationDto.setMobileNumber("081111111111");

        Registration registration = registrationMapper.dtoToEntity(registrationDto);

        given(registrationRepository.save(registration)).willAnswer(invocation -> invocation.getArgument(0));

        RegistrationDto saved = registrationService.create(registrationDto);

        assertThat(saved).isNotNull();

        verify(registrationRepository).save(any(Registration.class));

    }

    @Test
    void shouldReturnFindAll() {
        List<Registration> datas = new ArrayList();
        Registration registration = new Registration();
        registration.setId(1L);
        registration.setFirstName("xxxx");
        registration.setLastName("xxxx");
        registration.setEmail("xxxx@xxxx.com");
        registration.setMobileNumber("081111111111");
        datas.add(registration);

        given(registrationRepository.findAll()).willReturn(datas);

        List<RegistrationDto> registrationDtoList = StreamSupport.stream(datas.spliterator(), false)
                .map(registrationMapper::entityToDto)
                .collect(Collectors.toList());

        List<RegistrationDto> expected = registrationService.getAllData();

        assertEquals(expected, registrationDtoList);
    }

    @Test
    void findById(){
        final Long id = 1L;
        final Registration registration = new Registration();
        registration.setFirstName("xxxx");
        registration.setLastName("xxxx");
        registration.setEmail("xxxx@xxxx.com");
        registration.setMobileNumber("081111111111");

        given(registrationRepository.findById(id)).willReturn(Optional.of(registration));

        RegistrationDto data = registrationService.getById(id);

        assertThat(data).isNotNull();

    }
}
