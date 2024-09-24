package com.matzip.matzipback.board.command.application.service;

import com.matzip.matzipback.board.command.domain.repository.PostDomainRepository;
import com.matzip.matzipback.board.command.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostCommandService {

    private final PostDomainRepository postDomainRepository;

    public boolean savePostReport(PostDTO postDTO) {



//        postDomainRepository.save();
        return false;

    }
}
