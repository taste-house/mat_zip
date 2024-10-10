package com.matzip.matzipuser.users.command.domain.service;

import com.matzip.matzipuser.exception.ErrorCode;
import com.matzip.matzipuser.exception.RestApiException;
import com.matzip.matzipuser.users.command.application.dto.UpdateUserStatusDTO;
import com.matzip.matzipuser.users.command.domain.aggregate.Users;
import com.matzip.matzipuser.users.command.domain.repository.UsersDomainRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersDomainService {

    private final UsersDomainRepository usersDomainRepository;
    private final ModelMapper modelMapper;

    // 회원 활동 상태 수정 로직
    public void updateUserStatus(UpdateUserStatusDTO updateUserStatusDTO) {

        Users foundUser = usersDomainRepository.findById(updateUserStatusDTO.getUserSeq())
                .orElseThrow(() -> new RestApiException(ErrorCode.NOT_FOUND));

        modelMapper.map(updateUserStatusDTO, foundUser);
    }
}
