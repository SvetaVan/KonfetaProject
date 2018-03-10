package ru.candy.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class HelloWorldController {

    @GetMapping("/load")
    @ResponseBody
    public String loadAllAccounts (){
        return "HelloWorldLoadAccout";
    }




}
