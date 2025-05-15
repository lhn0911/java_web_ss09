package com.rikkei.ss09.service;

import com.rikkei.ss09.model.Schedule;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findAllScheduleByMovie(Long movieId);
    Schedule findById(Long id);
}