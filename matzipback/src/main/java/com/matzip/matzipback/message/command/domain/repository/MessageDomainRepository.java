package com.matzip.matzipback.message.command.domain.repository;

import com.matzip.matzipback.message.command.domain.aggregate.Messages;

import java.util.Optional;

public interface MessageDomainRepository {

    Messages save(Messages createdMessage);

    Optional<Messages> findById(Long messageSeq);
}
