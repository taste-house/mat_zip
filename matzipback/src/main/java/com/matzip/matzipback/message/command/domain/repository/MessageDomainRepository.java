package com.matzip.matzipback.message.command.domain.repository;

import com.matzip.matzipback.message.command.domain.aggregate.Messages;

public interface MessageDomainRepository {

    Messages save(Messages createdMessage);
}
