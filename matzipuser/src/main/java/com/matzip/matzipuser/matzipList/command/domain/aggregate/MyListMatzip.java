package com.matzip.matzipuser.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void updateListSeq(Long listSeq) {
        this.listSeq = listSeq;
    }

    public void updateListMatzipSeq(Long listMatzipSeq) {
        this.listMatzipSeq = listMatzipSeq;
    }

    public void updateRestaurantSeq(Long restaurantSeq) {
        this.restaurantSeq = restaurantSeq;
    }

    public void updateListMatzipComment(String listMatzipComment) {
        this.listMatzipComment = listMatzipComment;
    }

    public void createListSeq(@NotNull Long listSeq) {
        this.listSeq = listSeq;
    }

    public void createRestaurantSeq(@NotNull Long restaurantSeq) {
        this.restaurantSeq = restaurantSeq;
    }

    public void createListMatzipComment(String listMatzipComment) {
        this.listMatzipComment = listMatzipComment;
    }
}
