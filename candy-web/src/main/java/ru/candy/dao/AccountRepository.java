package ru.candy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepository {

    @Autowired
    private DataSource dataSource;

    public List<Account> loadAll() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        return jdbcTemplate.query("select id, name from Accounts",
                (rs, i) ->
                    new Account(
                        rs.getLong("id"),
                        rs.getString("name")
                    )
        );

    }

    public Account save(Account account) {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                "INSERT INTO Accounts(name) VALUES (:name)",
                new MapSqlParameterSource()
                        .addValue("name", account.getName()),
                keyHolder
        );
        account.setId((Long) keyHolder.getKey());
        return account;
    }

    public List<Account> loadAllJDBC() throws SQLException {
        String command = "SELECT id, name from Accounts";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(command);
        ) {
            List<Account> accountList = new ArrayList<>();
            while (resultSet.next()) {
                accountList.add(new Account(resultSet.getLong(1), resultSet.getString(2)));
            }
            return accountList;
        }
    }


}





