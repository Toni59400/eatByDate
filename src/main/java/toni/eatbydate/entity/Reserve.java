package toni.eatbydate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "t_reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Long id;

    @Column(name = "reserve_nom")
    private String ReserveNom;

    @ManyToOne
    @JoinColumn(name = "reserve_type_id")
    private ReserveType reserveType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

