package org.hyunjooon.communication_devtools.domain.account.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Gender;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Role;
import org.hyunjooon.communication_devtools.domain.account.user.enums.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity @Table(name = "users") @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @Comment(value = "사용자 ID")
    private String userId; // 사용자 고유 ID

    @Column(nullable = false) @Comment(value = "사용자 이름") private String userName; // 사용자 이름
    @Column(nullable = false, unique = true) @Comment(value = "사용자 이메일") private String email; // 사용자 이메일
    @Column(nullable = false) @Comment(value = "사용자 전화번호") private String phoneNumber; // 사용자 전화번호
    @Column(nullable = false) @Comment(value = "사용자 나이") @Min(value = 14, message = "나이는 14세 이상이여야 합니다") private int age; // 사용자 나이

    @Column(nullable = false) @Enumerated(value = EnumType.STRING) @Comment("사용자 성별") private Gender gender; // 사용자 성별
    @Comment(value = "사용자 깃허브 ID") private String githubId;
    @Comment(value = "사용자 비밀번호") @Column(nullable = false) private String password; // 사용자 비밀번호

    @Comment(value = "사용자 Oauth Provider") private String provider; // 사용자 Oauth provider
    @Comment(value = "사용자 Oauth providerId") private String providerId; // 사용자 Oauth providerId

    @Enumerated(value = EnumType.STRING) @Comment("사용자 상태") private Status status; // 사용자 상태(ONLINE: 접속, OFFLINE: 접속해제, DO_NOT_DISTURBED: 방해금지)

    @Comment(value = "사용자 프로필 이미지 이름") private String profileImageName; // 사용자 프로필 이미지 이름
    @Comment(value = "사용자 설명") private String profileDescription; // 사용자 프로필 기타 설명

    @ElementCollection private List<String> interested = new ArrayList<>(); // 사용자 관심사

    @Column(nullable = false) @Enumerated(value = EnumType.STRING) private Role role; // 사용자 권한 (ROLE_USER: 일반 사용자, ROLE_ADMIN: 어드민)


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return age == user.age && Objects.equals(userId, user.userId) && Objects.equals(userName, user.userName) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber) && gender == user.gender && Objects.equals(githubId, user.githubId) && Objects.equals(password, user.password) && Objects.equals(provider, user.provider) && Objects.equals(providerId, user.providerId) && status == user.status && Objects.equals(profileImageName, user.profileImageName) && Objects.equals(profileDescription, user.profileDescription) && Objects.equals(interested, user.interested) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, email, phoneNumber, age, gender, githubId, password, provider, providerId, status, profileImageName, profileDescription, interested, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", githubId='" + githubId + '\'' +
                ", password='" + password + '\'' +
                ", provider='" + provider + '\'' +
                ", providerId='" + providerId + '\'' +
                ", status=" + status +
                ", profileImageName='" + profileImageName + '\'' +
                ", profileDescription='" + profileDescription + '\'' +
                ", interested=" + interested +
                ", role=" + role +
                '}';
    }

    @Builder
    public User(String userId, String userName, String email, String phoneNumber, int age, Gender gender, String githubId, String password, String provider, String providerId, Status status, String profileImageName, String profileDescription, List<String> interested, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.gender = gender;
        this.githubId = githubId;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
        this.status = status;
        this.profileImageName = profileImageName;
        this.profileDescription = profileDescription;
        this.interested = interested;
        this.role = role;
    }
}
