package com.matzip.matzipback.users.command.infrastructure.repository;

import com.matzip.matzipback.users.command.domain.aggregate.Messages;
import com.matzip.matzipback.users.command.domain.repository.MessageRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageInfraRepository extends JpaRepository<Messages, Long>, MessageRepository {
}
