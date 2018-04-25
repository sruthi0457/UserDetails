package com.stech.user.repository;

import com.stech.user.domain.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler extends ResourceAssembler<UserDetails, UserResource> {
    @Override
    public UserResource toResource(UserDetails user) {
        UserResource userResource = new UserResource(user);
        return userResource;
    }
}
