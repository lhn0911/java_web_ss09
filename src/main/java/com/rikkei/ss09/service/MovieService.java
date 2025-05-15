package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
}
