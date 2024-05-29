package toni.eatbydate.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link toni.eatbydate.entity.Reserve}
 */
@Value
public class ReserveDto implements Serializable {
    private static final long serialVersionUID = 1140140367740942176L;
    Long id;
    String ReserveNom;
    ReserveTypeDto reserveType;
    UserDto user;
}