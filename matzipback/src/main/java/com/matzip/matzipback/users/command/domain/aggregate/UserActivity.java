package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_activity")
public class UserActivity {

    @Id
    private Long activityUserSeq;
    private Long activityLevelSeq;
    private Long activityPoint;
    private String influencerYn;

}
