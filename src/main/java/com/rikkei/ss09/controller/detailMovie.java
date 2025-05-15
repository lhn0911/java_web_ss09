package com.rikkei.ss09.controller;

import com.rikkei.ss09.model.Movie;
import com.rikkei.ss09.model.Schedule;
import com.rikkei.ss09.service.MovieService;
import com.rikkei.ss09.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class detailMovie {
    @Autowired
    private MovieService movieService;

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/detail")
    public String detailMovie(@RequestParam("id") Long id, Model model) {
        Movie movie = movieService.findById(id);
        List<Schedule> schedules = scheduleService.findAllScheduleByMovie(id);
        model.addAttribute("movie", movie);
        model.addAttribute("schedules", schedules);
        return "detailMovie";
    }
}
