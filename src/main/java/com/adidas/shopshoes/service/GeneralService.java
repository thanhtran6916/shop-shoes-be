package com.adidas.shopshoes.service;

import java.util.List;

public interface GeneralService<T> {
    T save(T t);
    T update(T t);
    T findById(String id);
    List<T> findAll();
}
