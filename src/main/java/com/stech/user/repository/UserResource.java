package com.stech.user.repository;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stech.user.domain.UserDetails;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class UserResource extends ResourceSupport {
    String firstName;
    String lastName;
    Long id;
    String email;

    public UserResource(UserDetails user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        id = user.getId();
        email = user.getEmail();
    }

    @JsonProperty("id")
    public Long getResourceId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

}
