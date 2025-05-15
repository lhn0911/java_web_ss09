package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Schedule;
import com.rikkei.ss09.repository.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImp implements ScheduleService {
    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<Schedule> findAllScheduleByMovie(Long movieId) {
        return scheduleDao.findAllScheduleByMovie(movieId);
    }
    @Override
    public Schedule findById(Long id) {
        return scheduleDao.findById(id);
    }
}