package org.hyunjooon.communication_devtools.domain.account.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Gender;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Role;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Status;
import org.hyunjooon.communication_devtools.domain.board.Board;
import org.hyunjooon.communication_devtools.domain.board_comment.BoardComment;
import org.hyunjooon.communication_devtools.domain.post.Post;
import org.hyunjooon.communication_devtools.domain.room.ChatRoom;
import org.hyunjooon.communication_devtools.domain.room_user.RoomUser;
import org.hyunjooon.communication_devtools.domain.source_code.SourceCode;
import org.hyunjooon.communication_devtools.domain.source_code_review.SourceCodeReview;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "users") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private String userId; // 사용자 고유 ID

    @Column(nullable = false) private String userName; // 사용자 이름
    @Column(nullable = false, unique = true) private String email; // 사용자 이메일
    @Column(nullable = false) private String phoneNumber; // 사용자 전화번호
    @Column(nullable = false) @Min(value = 14, message = "나이는 14세 이상이여야 합니다") private int age; // 사용자 나이

    @Column(nullable = false) @Enumerated(value = EnumType.STRING) private Gender gender; // 사용자 성별
    @Column(nullable = false) private String password; // 사용자 비밀번호

    private String provider; // 사용자 Oauth provider
    private String providerId; // 사용자 Oauth providerId

    @Enumerated(value = EnumType.STRING) private Status status; // 사용자 상태(ONLINE: 접속, OFFLINE: 접속해제, DO_NOT_DISTURBED: 방해금지)

    private String profileImageName; // 사용자 프로필 이미지 이름
    private String profileImageUrl; // 사용자 프로필 이미지 경로
    private String profileDescription; // 사용자 프로필 기타 설명

    @ElementCollection private List<String> interested = new ArrayList<>(); // 사용자 관심사

    @Column(nullable = false) @Enumerated(value = EnumType.STRING) private Role role; // 사용자 권한 (ROLE_USER: 일반 사용자, ROLE_ADMIN: 어드민)

    // 내가 생성한(Host) ChatRoom
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoom> hostRooms= new ArrayList<>();

    public void addHostRooms(ChatRoom chatRoom) {
        chatRoom.setHostUser(this);
        this.hostRooms.add(chatRoom);
    }

    // 자신이 속한 채팅방 (RoomUser) 엔티티와 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomUser> rooms = new ArrayList<>();

    public void addRooms(RoomUser roomUser) {
        roomUser.setUser(this);
        this.rooms.add(roomUser);
    }

    // 자신이 올린 게시물 (Board) 엔티티와 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    public void addBoards(Board board) {
        board.setUser(this);
        this.boards.add(board);
    }

    // 자신이 올림 게시물 (Post) 엔티티와 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public void addPosts(Post post) {
        post.setUser(this);
        this.posts.add(post);
    }

    // 자신이 작성한 게시물 댓글 (BoardComment) 엔티티 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardComment> comments = new ArrayList<>();

    public void addComments(BoardComment boardComment) {
        boardComment.setUser(this);
        this.comments.add(boardComment);
    }

    // SourceCode 엔티티 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SourceCode> sourceCodes = new ArrayList<>();

    public void addSourceCodes(SourceCode sourceCode) {
        sourceCode.setUser(this);
        this.sourceCodes.add(sourceCode);
    }

    // SourceCodeReview 엔티티 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SourceCodeReview> sourceCodeReviews = new ArrayList<>();

    public void addSourceCodeReviews(SourceCodeReview sourceCodeReview) {
        sourceCodeReview.setUser(this);
        this.sourceCodeReviews.add(sourceCodeReview);
    }

    @Builder
    public User(String userId, String userName, String email, String phoneNumber, int age, Gender gender, String password, String provider, String providerId, Status status, String profileImageName, String profileImageUrl, String profileDescription, List<String> interested, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.status = status;
        this.profileImageName = profileImageName;
        this.profileImageUrl = profileImageUrl;
        this.profileDescription = profileDescription;
        this.interested = interested;
        this.role = role;
    }
}
