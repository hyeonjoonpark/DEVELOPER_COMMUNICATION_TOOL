package org.hyunjooon.communication_devtools.domain.auth.oauth.dto;

import org.hyunjooon.communication_devtools.domain.account.user.User;

import java.io.Serializable;

public record SessionUser(
        String name,
        String email,
        String picture
) implements Serializable {
    public SessionUser(User user) {
        this(user.getUserName(), user.getEmail(), user.getProfileImageName());
    }
}
