package com.matzip.matzipback.users.command.domain.repository;


import com.matzip.matzipback.users.command.domain.aggregate.Messages;

public interface MessageRepository {
    Messages save(Messages createdMessage);
}
