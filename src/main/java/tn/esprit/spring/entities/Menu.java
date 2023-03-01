package tn.esprit.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menus")

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int menuId;

    private String description;

    @Column(name = "menu_name")
    private String menuName;

    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> dishes;
}
