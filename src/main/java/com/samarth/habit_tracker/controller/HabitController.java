package com.samarth.habit_tracker.controller;

import com.samarth.habit_tracker.model.Habit;
import com.samarth.habit_tracker.service.HabitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habits")
public class HabitController {

    private final HabitService habitService;

    @Autowired
    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    // Create a new habit
    @PostMapping
    public ResponseEntity<Habit> createHabit(@RequestBody Habit habit, @RequestParam Long userId) {
        Habit createdHabit = habitService.createHabit(habit, userId);
        if (createdHabit != null) {
            return new ResponseEntity<>(createdHabit, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get all habits
    @GetMapping
    public ResponseEntity<List<Habit>> getAllHabits() {
        List<Habit> habits = habitService.getAllHabits();
        return new ResponseEntity<>(habits, HttpStatus.OK);
    }

    // Get habit by ID
    @GetMapping("/{id}")
    public ResponseEntity<Habit> getHabitById(@PathVariable Long id) {
        Habit habit = habitService.getHabitById(id);
        if (habit != null) {
            return new ResponseEntity<>(habit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update habit by ID
    @PutMapping("/{id}")
    public ResponseEntity<Habit> updateHabit(@PathVariable Long id, @RequestBody Habit habit) {
        Habit updatedHabit = habitService.updateHabit(id, habit);
        if (updatedHabit != null) {
            return new ResponseEntity<>(updatedHabit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete habit by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@PathVariable Long id) {
        boolean isDeleted = habitService.deleteHabit(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
