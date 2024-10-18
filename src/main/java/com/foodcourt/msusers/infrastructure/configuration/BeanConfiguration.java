package com.foodcourt.msusers.infrastructure.configuration;

import com.foodcourt.msusers.domain.api.IOwnerServicePort;
import com.foodcourt.msusers.domain.api.IRoleServicePort;
import com.foodcourt.msusers.domain.spi.IOwnerPersistancePort;
import com.foodcourt.msusers.domain.spi.IRolePersistancePort;
import com.foodcourt.msusers.domain.usecase.OwnerUseCase;
import com.foodcourt.msusers.domain.usecase.RoleUseCase;
import com.foodcourt.msusers.domain.validations.ValidationService;
import com.foodcourt.msusers.domain.validations.impl.ValidationServiceImplement;
import com.foodcourt.msusers.infrastructure.out.jpa.adapter.OwnerAdapter;
import com.foodcourt.msusers.infrastructure.out.jpa.adapter.RoleAdapter;
import com.foodcourt.msusers.infrastructure.out.jpa.mapper.OwnerEntityMapper;
import com.foodcourt.msusers.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.foodcourt.msusers.infrastructure.out.jpa.repository.IOwnerRepository;
import com.foodcourt.msusers.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class BeanConfiguration {

    private final IOwnerRepository ownerRepository;
    private final OwnerEntityMapper ownerEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    public BeanConfiguration(IOwnerRepository ownerRepository, OwnerEntityMapper ownerEntityMapper, IRoleRepository roleRepository, RoleEntityMapper roleEntityMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerEntityMapper = ownerEntityMapper;
        this.roleRepository = roleRepository;
        this.roleEntityMapper = roleEntityMapper;
    }

    @Bean
    public ValidationService validationService() {
        return new ValidationServiceImplement();
    }

    @Bean
    public IRolePersistancePort rolePersistancePort() {
        return new RoleAdapter(roleRepository, roleEntityMapper);
    }


    @Bean
    public IOwnerPersistancePort ownerPersistancePort(){
        return new OwnerAdapter(ownerEntityMapper, ownerRepository);
    }


    @Bean
    public IOwnerServicePort ownerServicePort(){
        return new OwnerUseCase(ownerPersistancePort(), validationService(), rolePersistancePort(), passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
