package com.samarth.habit_tracker.repository;

import com.samarth.habit_tracker.model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

    // find HabitLogs by Habit ID
    List<HabitLog> findByHabitId(Long habitId);

    // find HabitLog by Habit ID and Date
    Optional<HabitLog> findByHabitIdAndDate(Long habitId, LocalDate date);

    // find HabitLogs by completion status (returns a list)
    List<HabitLog> findByCompleted(boolean completed);
}
