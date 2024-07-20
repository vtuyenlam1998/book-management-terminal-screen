package com.lamvt.service;

import java.util.List;

public interface CommonService<T> {
    List<T> findAll();
    T findById(int id);
    void save(T t);
    void delete(T t);
}
