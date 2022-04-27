package com.shop.services;

import com.shop.exceptions.NotFoundException;
import com.shop.models.Basket;
import com.shop.repositories.BasketRepository;
import com.shop.validators.BasketValidator;

import java.util.List;
import java.util.Optional;

public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketValidator validator;

    public BasketService(
        BasketRepository basketRepository,
        BasketValidator validator
    ) {
        this.basketRepository = basketRepository;
        this.validator = validator;
    }

    public List<Basket> getBaskets() {
        return basketRepository.getBaskets();
    }

    public Basket addBasket(Basket basket, int personId) {
        validator.validate(basket);
        basketRepository.addBasket(basket, personId);
        return basket;
    }

    public void deleteBasket(int id) {
        validator.validate(id);
        basketRepository.deleteBasket(id);
    }

    public Basket getBasket(int id) {
        validator.validate(id);
        Optional<Basket> basket = basketRepository.getBasket(id);
        if (basket.isEmpty()) {
            throw new NotFoundException("Basket not found");
        } else {
            return basket.get();
        }
    }
}
