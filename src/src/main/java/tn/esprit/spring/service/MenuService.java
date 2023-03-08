package tn.esprit.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import tn.esprit.spring.entities.Dish;
import tn.esprit.spring.entities.Menu;
import tn.esprit.spring.repository.DishRepository;
import tn.esprit.spring.repository.MenuRepository;
import tn.esprit.spring.serviceInterface.ImenuService;

import java.util.List;
import java.util.Optional;


@Service
@Component
public class MenuService implements ImenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;
    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenuById(int menuId) {
        Optional<Menu> optionalMenu = menuRepository.findById(menuId);
        return optionalMenu.orElse(null);
    }

    public void deleteMenu(int menuId) {
        menuRepository.deleteById(menuId);
    }
    public void addDishToMenu(int dishId, int menuId) {
        Dish dish = dishRepository.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Dish not found with id " + dishId));
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id " + menuId));
        menu.getDishes().add(dish);
        dish.setMenu(menu);
        menuRepository.save(menu);
    }

}
