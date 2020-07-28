package com.springboot.georlock.mapper;

import com.springboot.georlock.dto.Login;

import java.util.List;

public interface AccessMapper {
    public List<Login> getAll() throws Exception;


    public void update(Login login) throws Exception;

    public void delete(String empNo) throws Exception;

    public  void insert(Login login) throws Exception;

    public List<Login> Search(String textSearch) throws Exception;

    public List<Login> emplist() throws Exception;

    public List<Login> empSearch(String textSearch) throws Exception;
}
