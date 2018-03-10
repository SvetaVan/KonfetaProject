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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AccountRepository {

    @Autowired
    private DataSource dataSource;

    public List<Account> loadAll() {
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        List<Account> accounts = jdbcTemplate.query("select id, name from Accounts",
                new RowMapper<Account>() {
                    @Nullable
                    @Override
                    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Account(
                                rs.getLong("id"),
                                rs.getString("name")
                        );
                    }
                }
        );
        return accounts;
    }

    public Account save(Account account){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
               "INSERT INTO Accounts(name) VALUES (:name)",
               new MapSqlParameterSource()
               .addValue("name", account.getName()),
               keyHolder
        );
        account.setId((Long)keyHolder.getKey());
        return account;
    }
    public void  setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }




}





