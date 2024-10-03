package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "lists")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@SQLDelete(sql = "UPDATE lists SET list_status = 'delete', list_deleted_time = NOW() WHERE list_seq = ?")
public class MyList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listSeq;
    private Long listUserSeq;
    private String listTitle;
    private String listContent;
    private String listStatus;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime listCreatedTime;
    @LastModifiedDate
    private LocalDateTime listUpdatedTime;
    private LocalDateTime listDeletedTime;
    private int listLevel;


    @PrePersist
    public void prePersist() {
        if (listStatus == null) listStatus = "active";
    }


    private MyList(Long listUserSeq, String listTitle, String listContent, int listLevel) {
        this.listUserSeq = listUserSeq;
        this.listTitle = listTitle;
        this.listContent = listContent;
        this.listLevel = listLevel;
    }


    public static MyList create(Long listUserSeq, String listTitle, String listContent, int listLevel) {
        return new MyList(listUserSeq, listTitle, listContent, listLevel);
    }

    public void updateListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public void updateListContent(String listContent) {
        this.listContent = listContent;
    }


    public void updateListLevel(int listLevel) {
        this.listLevel = listLevel;
    }
}
