package com.matzip.matzipback.matzipList.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Entity
@Table(name = "list")
@NoArgsConstructor
@SQLDelete(sql = "UPDATE list SET list_status = 'delete', list_deleted_time = NOW() WHERE list_seq = ?")
public class MyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long listSeq;
    private long listUserSeq;
    private String listTitle;
    private String listContent;
    private String listStatus;
    @CreatedDate
    private LocalDateTime listCreatedTime;
    @LastModifiedDate
    private LocalDateTime listUpdatedTime;
    private LocalDateTime listDeletedTime;
    private int listLevel;
}
