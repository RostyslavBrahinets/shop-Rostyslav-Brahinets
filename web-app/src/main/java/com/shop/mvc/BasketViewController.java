package com.shop.mvc;

import com.shop.models.Basket;
import com.shop.models.Person;
import com.shop.services.BasketService;
import com.shop.services.PersonService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketViewController {
    private final PersonService personService;
    private final BasketService basketService;

    public BasketViewController(
        PersonService personService,
        BasketService basketService
    ) {
        this.personService = personService;
        this.basketService = basketService;
    }

    @GetMapping()
    public String index(
        @AuthenticationPrincipal UserDetails userDetails,
        Model model
    ) {
        Person person = personService.getPerson(userDetails.getUsername());
        Basket basket = basketService.getBasketByPerson(person.getId());
        model.addAttribute("id", basket.getId());
        return "basket/index";
    }
}
