package com.shop.dao;

import com.shop.models.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class PersonRoleDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PersonRoleDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Role> findRoleForPerson(long personId) {
        return jdbcTemplate.query(
                "SELECT * FROM role r, person_role pr "
                    + "WHERE pr.role_id=r.id and pr.person_id=:person_id",
                Map.ofEntries(Map.entry("person_id", personId)),
                new BeanPropertyRowMapper<>(Role.class)
            )
            .stream().findAny();
    }

    public void saveRoleForPerson(long personId, long roleId) {
        jdbcTemplate.update(
            "INSERT INTO person_role (person_id, role_id) VALUES (:person_id, :role_id)",
            Map.ofEntries(
                Map.entry("person_id", personId),
                Map.entry("role_id", roleId)
            )
        );
    }

    public void updateRoleForPerson(long personId, long roleId) {
        jdbcTemplate.update(
            "UPDATE person_role SET role_id=:role_id WHERE person_id=:person_id",
            Map.ofEntries(
                Map.entry("person_id", personId),
                Map.entry("role_id", roleId)
            )
        );
    }
}
