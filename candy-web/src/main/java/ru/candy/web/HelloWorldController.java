package ru.candy.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.candy.dao.Account;
import ru.candy.dao.AccountRepository;

import javax.sql.DataSource;

import java.util.List;


@RestController
@RequestMapping("/accounts")
public class HelloWorldController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/load")
    @ResponseBody
    public String loadAllAccounts() {
        return toString(accountRepository.loadAll());
    }

    private String toString(List<Account> accountsList) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<body>" +
                              "<table>" +
                                  "<caption>Accounts</caption>" +
                                  "<tr><th>ID</th ><th > Account_Name </th ></tr > ");
        for (Account account : accountsList) {
            stringBuilder.append("<tr><td>" + account.getId() + "</td><td>" + account.getName() + "</td></tr>");
        }
        stringBuilder.append("</table>" +
                                 "</body>");
        return stringBuilder.toString();

    }
    @RequestMapping(value = "/loadAsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Account> loadAllAccountsAsList(){
        return accountRepository.loadAll();
    }


}
