package ru.candy.dao;

import com.google.common.base.MoreObjects;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class Account {
    private String name;
    private long id;

    public Account(String name){
        this.name = name;
    }

    public Account(long id, String name){
        this.name = name;
        this.id = id;
    }

    void  setId (long id){
        this.id = id;
    }

    public  String getName (){
        return name;
    }

    public long getId(){
        return id;
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("id", id)
                .toString();
    }
}
