package com.example.demo.service;

import java.util.List;

public interface BaseService<Req, T, Res> {
    Res save(Req req);
    List<Res> findAll();
    Res findById(T t);
    Res update(T t, Req req);
    void delete(T t);

}
