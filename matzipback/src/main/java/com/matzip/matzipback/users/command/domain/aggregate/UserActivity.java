package com.matzip.matzipback.users.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_activity")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserActivity {

    @Id
    private Long activityUserSeq;
    private Long activeLevelSeq;
    private Integer activityPoint;
    private String influencerYn;

    private UserActivity(Long activityUserSeq) {
        this.activityUserSeq = activityUserSeq;
    }

    public static UserActivity create(Long activityUserSeq) {
        return new UserActivity(activityUserSeq);
    }


    public void changePoint(int point) {
        this.activityPoint = activityPoint + point;
    }

    public void changeLevel(long level) {
        this.activeLevelSeq = level;
    }

    @PrePersist
    public void prePersist() {
        if (this.influencerYn == null) this.influencerYn = "N";
        if (this.activityPoint == null) this.activityPoint = 0;
        if (this.activeLevelSeq == null) this.activeLevelSeq = 1L;
    }
}
