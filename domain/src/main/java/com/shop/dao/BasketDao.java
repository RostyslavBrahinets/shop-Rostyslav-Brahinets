package com.shop.dao;

import com.shop.db.DatabaseTemplate;
import com.shop.models.Basket;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BasketDao {
    private final NamedParameterJdbcTemplate jdbcTemplate = DatabaseTemplate.getJdbcTemplate();

    public List<Basket> getBaskets() {
        return jdbcTemplate.query(
            "SELECT * FROM basket",
            new BeanPropertyRowMapper<>(Basket.class)
        );
    }

    public void addBasket(Basket basket, int personId) {
        String sql = "INSERT INTO basket (total_cost, person_id)"
            + " VALUES (:total_cost, :person_id)";

        Map<String, Object> param = Map.of(
            "total_cost", basket.getTotalCost(),
            "person_id", personId
        );

        jdbcTemplate.update(sql, param);
    }

    public void updateBasket(int id, Basket updatedBasket) {
        String sql = "UPDATE basket SET total_cost=:total_cost WHERE id=:id";

        Map<String, Object> param = Map.of(
            "total_cost", updatedBasket.getTotalCost(),
            "id", id
        );

        jdbcTemplate.update(sql, param);
    }

    public void deleteBasket(int id) {
        String sql = "DELETE FROM contact WHERE id=:id";
        Map<String, Integer> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Basket> getBasket(int id) {
        Map<String, Integer> param = Map.of("id", id);

        Basket basket = jdbcTemplate.query(
                "SELECT * FROM basket WHERE id=:id",
                param,
                new BeanPropertyRowMapper<>(Basket.class)
            )
            .stream().findAny().orElse(null);

        return Optional.ofNullable(basket);
    }
}
