package com.matzip.matzipback.users.command.application.service;

import com.matzip.matzipback.users.command.domain.aggregate.UserActivity;
import com.matzip.matzipback.users.command.domain.aggregate.Users;
import com.matzip.matzipback.users.command.domain.repository.UsersDomainRepository;
import com.matzip.matzipback.users.command.domain.service.UserActivityDomainService;
import com.matzip.matzipback.users.command.dto.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersCommandService {

    private final UsersDomainRepository usersDomainRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserActivityDomainService userActivityDomainService;

    public void createUser(CreateUserRequest newUser) {
        Users users = modelMapper.map(newUser, Users.class);
        users.encryptPassword(passwordEncoder.encode(newUser.getUserPassword()));
        usersDomainRepository.save(users);

        userActivityDomainService.saveUserActivity(UserActivity.create(users.getUserSeq()));
    }
}
