package toni.eatbydate.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link toni.eatbydate.entity.User}
 */
@Value
public class UserDto implements Serializable {
    Long userId;
    String username;
    String passwordHash;
    String emailAddress;
}