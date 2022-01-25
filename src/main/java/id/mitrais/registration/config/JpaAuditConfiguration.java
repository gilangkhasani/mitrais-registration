/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.mitrais.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author gpk
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {

    /*
          if you are using spring security, you can get the currently logged username with following code segment.
          SecurityContextHolder.getContext().getAuthentication().getName()
    return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
     */
        return () -> {
            if (SecurityContextHolder.getContext().getAuthentication() != null) {
//                Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                return Optional.ofNullable(principal.getClaimAsString("preferred_username"));
                return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            } else {
                return Optional.ofNullable("Unknown");
            }
        };
    }
}
