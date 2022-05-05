package com.shop.dao;

import com.shop.db.DatabaseTemplate;
import com.shop.models.Wallet;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class WalletDao {
    private final NamedParameterJdbcTemplate jdbcTemplate = DatabaseTemplate.getJdbcTemplate();

    public List<Wallet> getWallets() {
        return jdbcTemplate.query(
            "SELECT * FROM wallet",
            new BeanPropertyRowMapper<>(Wallet.class)
        );
    }

    public void addWallet(Wallet wallet, long personId) {
        String sql = "INSERT INTO wallet (number, amount_of_money, person_id)"
            + " VALUES (:number, :amount_of_money, :person_id)";

        Map<String, Object> param = Map.of(
            "number", wallet.getNumber(),
            "amount_of_money", wallet.getAmountOfMoney(),
            "person_id", personId
        );

        jdbcTemplate.update(sql, param);
    }

    public void updateWallet(long id, Wallet updatedWallet) {
        String sql = "UPDATE wallet SET amount_of_money=:amount_of_money "
            + "WHERE id=:id";

        Map<String, Object> param = Map.of(
            "amount_of_money", updatedWallet.getAmountOfMoney(),
            "id", id
        );

        jdbcTemplate.update(sql, param);
    }

    public void deleteWallet(long id) {
        String sql = "DELETE FROM wallet WHERE id=:id";
        Map<String, Long> param = Map.of("id", id);
        jdbcTemplate.update(sql, param);
    }

    public Optional<Wallet> getWallet(long id) {
        Map<String, Long> param = Map.of("id", id);

        Wallet wallet = jdbcTemplate.query(
                "SELECT * FROM wallet WHERE id=:id",
                param,
                new BeanPropertyRowMapper<>(Wallet.class)
            )
            .stream().findAny().orElse(null);

        return Optional.ofNullable(wallet);
    }
}
