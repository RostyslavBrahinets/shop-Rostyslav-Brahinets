package com.shop.mvc;

import com.shop.models.Person;
import com.shop.services.PersonService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wallet")
public class WalletViewController {
    private final PersonService personService;

    public WalletViewController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String index(
        @AuthenticationPrincipal UserDetails userDetails,
        Model model
    ) {
        Person person = personService.getPerson(userDetails.getUsername());
        model.addAttribute("id", person.getId());
        return "wallet/index";
    }
}