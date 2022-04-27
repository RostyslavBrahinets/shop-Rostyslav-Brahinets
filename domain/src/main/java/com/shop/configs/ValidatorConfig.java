package com.shop.configs;

import com.shop.repositories.*;
import com.shop.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RepositoryConfig.class)
public class ValidatorConfig {
    @Bean
    public BasketValidator contactValidator(BasketRepository basketRepository) {
        return new BasketValidator(basketRepository);
    }

    @Bean
    public ContactValidator contactValidator(ContactRepository contactRepository) {
        return new ContactValidator(contactRepository);
    }

    @Bean
    public PersonValidator personValidator(PersonRepository personRepository) {
        return new PersonValidator(personRepository);
    }

    @Bean
    public ProductValidator personValidator(ProductRepository productRepository) {
        return new ProductValidator(productRepository);
    }

    @Bean
    public WalletValidator personValidator(WalletRepository walletRepository) {
        return new WalletValidator(walletRepository);
    }
}
