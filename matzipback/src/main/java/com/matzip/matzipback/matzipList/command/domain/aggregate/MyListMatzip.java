package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "list_matzip")
@NoArgsConstructor
@Getter
public class MyListMatzip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listMatzipSeq;
    private Long listSeq;
    private Long restaurantSeq;
    private String listMatzipComment;

    public MyListMatzip(Long listSeq, Long restaurantSeq, String listMatzipComment) {
        this.listSeq = listSeq;
        this.restaurantSeq = restaurantSeq;
        this.listMatzipComment = listMatzipComment;
    }

    public static MyListMatzip create(Long listSeq, Long restaurantSeq, String listMatzipComment) {
        return new MyListMatzip(listSeq, restaurantSeq, listMatzipComment);
    }

}
