package toni.eatbydate.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link toni.eatbydate.entity.ReserveType}
 */
@Value
public class ReserveTypeDto implements Serializable {
    Long id;
    String name;
}