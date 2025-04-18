package com.samarth.habit_tracker.repository;

import com.samarth.habit_tracker.model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    // Custom query to find habits by user id
    List<Habit> findByUserId(Long userId);

    // Custom query to find a habit by name (if needed)
    Optional<Habit> findByName(String name);
}
