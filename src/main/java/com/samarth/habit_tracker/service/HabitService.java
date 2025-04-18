package com.samarth.habit_tracker.service;

import com.samarth.habit_tracker.model.Habit;
import com.samarth.habit_tracker.model.User;
import com.samarth.habit_tracker.repository.HabitRepository;
import com.samarth.habit_tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;

    @Autowired
    public HabitService(HabitRepository habitRepository, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.userRepository = userRepository;
    }

    // Create a new habit
    public Habit createHabit(Habit habit, Long userId) {
        // Get user from repository
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            habit.setUser(userOptional.get()); // Set the user for the habit
            return habitRepository.save(habit);
        }
        return null;
    }

    // Get all habits
    public List<Habit> getAllHabits() {
        return habitRepository.findAll();
    }

    // Get habits by user ID
    public List<Habit> getHabitsByUser(Long userId) {
        return habitRepository.findByUserId(userId);
    }

    // Get habit by ID
    public Habit getHabitById(Long id) {
        return habitRepository.findById(id).orElse(null);
    }

    // Update a habit
    public Habit updateHabit(Long id, Habit habitDetails) {
        Optional<Habit> existingHabit = habitRepository.findById(id);
        if (existingHabit.isPresent()) {
            Habit habit = existingHabit.get();
            habit.setName(habitDetails.getName());
            habit.setDescription(habitDetails.getDescription());
            habit.setGoal(habitDetails.getGoal());
            habit.setFrequency(habitDetails.getFrequency());
            habit.setStatus(habitDetails.isStatus());
            habit.setUpdatedAt(habitDetails.getUpdatedAt());
            return habitRepository.save(habit);
        }
        return null;
    }

    // Delete a habit
    public boolean deleteHabit(Long id) {
        if (habitRepository.existsById(id)) {
            habitRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
