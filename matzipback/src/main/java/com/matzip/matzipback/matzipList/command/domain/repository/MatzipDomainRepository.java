package com.matzip.matzipback.matzipList.command.domain.repository;

import com.matzip.matzipback.matzipList.command.domain.aggregate.MyListMatzip;
import jakarta.validation.constraints.NotBlank;

public interface MatzipDomainRepository {
    MyListMatzip save(MyListMatzip newMatzip);

    void deleteById(@NotBlank Long listMatzipSeq);
}
