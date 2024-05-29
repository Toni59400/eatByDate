package toni.eatbydate;

import lombok.Value;
import toni.eatbydate.dto.ReserveDto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link toni.eatbydate.entity.Product}
 */
@Value
public class ProductDto implements Serializable {
    Long id;
    ReserveDto reserve;
    String apiId;
    Date expirationDate;
    int quantite;
}