package id.mitrais.registration.service;

import id.mitrais.registration.dto.RegistrationDto;
import id.mitrais.registration.mapper.RegistrationMapper;
import id.mitrais.registration.model.Registration;
import id.mitrais.registration.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    private RegistrationMapper registrationMapper;

    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, RegistrationMapper registrationMapper) {
        this.registrationRepository = registrationRepository;
        this.registrationMapper = registrationMapper;
    }

    public List<RegistrationDto> getAllData() {
        return StreamSupport.stream(registrationRepository.findAll().spliterator(), false)
                .map(registrationMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public RegistrationDto getById(Long id){
        return registrationRepository.findById(id).map(registrationMapper::entityToDto).orElse(null);
    }

    public RegistrationDto create(RegistrationDto input) {
        Registration registration = registrationRepository.save(registrationMapper.dtoToEntity(input));
        return registrationMapper.entityToDto(registration);
    }

}
