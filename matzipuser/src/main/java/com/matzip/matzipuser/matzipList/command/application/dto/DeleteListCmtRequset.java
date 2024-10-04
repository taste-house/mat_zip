package com.matzip.matzipuser.matzipList.command.application.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class DeleteListCmtRequset {
    private Long listSeq;
    @NotNull
    private Long listCommentSeq;
    private Long listCommentUserSeq;

    public DeleteListCmtRequset(long listSeq, long listCommentSeq, long listCommentUserSeq) {
        this.listSeq = listSeq;
        this.listCommentSeq = listCommentSeq;
        this.listCommentUserSeq = listCommentUserSeq;
    }
}
