package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> findAll();
    Movie findById(Long id);
}
