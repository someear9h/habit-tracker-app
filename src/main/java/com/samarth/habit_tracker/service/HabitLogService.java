package com.samarth.habit_tracker.service;

import com.samarth.habit_tracker.model.HabitLog;
import com.samarth.habit_tracker.repository.HabitLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HabitLogService {

    private final HabitLogRepository habitLogRepository;

    @Autowired
    public HabitLogService(HabitLogRepository habitLogRepository) {
        this.habitLogRepository = habitLogRepository;
    }

    // Create or update a habit log
    public HabitLog saveHabitLog(HabitLog habitLog) {
        return habitLogRepository.save(habitLog);
    }

    // Get all habit logs
    public List<HabitLog> getAllHabitLogs() {
        return habitLogRepository.findAll();
    }

    // Get habit log by ID
    public Optional<HabitLog> getHabitLogById(Long id) {
        return habitLogRepository.findById(id);
    }

    // Get habit logs by Habit ID
    public List<HabitLog> getHabitLogsByHabitId(Long habitId) {
        return habitLogRepository.findByHabitId(habitId);
    }

    // Get habit log by Habit ID and Date
    public Optional<HabitLog> getHabitLogByHabitIdAndDate(Long habitId, LocalDate date) {
        return habitLogRepository.findByHabitIdAndDate(habitId, date);
    }

    // Get habit logs by completion status
    public List<HabitLog> getHabitLogsByCompletionStatus(boolean completed) {
        return habitLogRepository.findByCompleted(completed);
    }

    // Delete habit log by ID
    public boolean deleteHabitLog(Long id) {
        if (habitLogRepository.existsById(id)) {
            habitLogRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
