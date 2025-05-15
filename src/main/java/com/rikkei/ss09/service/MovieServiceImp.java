package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Movie;
import com.rikkei.ss09.repository.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    private MovieDao movieDao;
    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieDao.findById(id);
    }
}
