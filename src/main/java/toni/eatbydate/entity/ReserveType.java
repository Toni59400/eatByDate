package toni.eatbydate.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "t_reserve_type")
public class ReserveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_type_id")
    private Long id;

    @Column(name = "reserve_type_name")
    private String name;

}
