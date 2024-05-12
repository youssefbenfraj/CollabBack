package tn.esprit.espritcollabbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Buys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBuy;
    @ManyToOne
    private Document document;
    @ManyToOne
    private User user;
}
