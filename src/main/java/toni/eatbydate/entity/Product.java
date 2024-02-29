package toni.eatbydate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "t_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reserve_id")
    private Reserve reserve;

    @Column(name = "product_api_id")
    private String apiId;

    @Column(name = "expiration_date")
    private Date expirationDate;

    private int quantite;

}

