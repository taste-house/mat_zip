package com.matzip.matzipback.message.command.infrastructure.repository;

import com.matzip.matzipback.message.command.domain.aggregate.Messages;
import com.matzip.matzipback.message.command.domain.repository.MessageDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageInfraRepository extends JpaRepository<Messages, Long>, MessageDomainRepository {
}
