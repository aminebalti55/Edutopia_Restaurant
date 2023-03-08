package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt= new Date();
    @JsonIgnore
    @OneToOne(mappedBy = "menu")
    private Restaurant restaurant;
    @JsonIgnore
    @OneToMany(orphanRemoval = true)
    private List<Dish> dishes;


}
