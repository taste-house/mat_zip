package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface MatzipDomainRepository {
    MyListMatzip save(MyListMatzip newMatzip);

    void deleteById(@NotBlank Long listMatzipSeq);

    Optional<MyListMatzip> findById(Long listMatzipSeq);
}
