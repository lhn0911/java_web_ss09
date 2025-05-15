package com.rikkei.ss09.repository;

import com.rikkei.ss09.model.Schedule;

import java.util.List;

public interface ScheduleDao {
    List<Schedule> findAllScheduleByMovie(Long movieId);
    Schedule findById(Long id);
}
