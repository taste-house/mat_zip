package com.matzip.matzipback.test.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_tag")
@NoArgsConstructor
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postTagSeq;
    private int tagSeq;
    private int postSeq;

}
