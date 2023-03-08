package tn.esprit.spring.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Menu;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.service.MenuService;

import java.util.List;

@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;


    @PostMapping("/")
    public Menu createMenu(@org.springframework.web.bind.annotation.RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    @GetMapping("/")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{menuId}")
    public Menu getMenuById(@PathVariable int menuId) {
        return menuService.getMenuById(menuId);
    }

    @DeleteMapping("/{menuId}")
    public void deleteMenu(@PathVariable int menuId) {
        menuService.deleteMenu(menuId);
    }
    @PostMapping("/{menuId}/addDish")
    public ResponseEntity<String> addDishToMenu(@PathVariable int menuId, @RequestParam int dishId) {
        menuService.addDishToMenu(dishId, menuId);
        return ResponseEntity.ok("Dish added to menu successfully.");
    }
}

