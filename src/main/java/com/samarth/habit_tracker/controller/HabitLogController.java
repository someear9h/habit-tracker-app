package com.samarth.habit_tracker.controller;

import com.samarth.habit_tracker.model.HabitLog;
import com.samarth.habit_tracker.service.HabitLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habit-logs")
public class HabitLogController {

    private final HabitLogService habitLogService;

    @Autowired
    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    // create or update a habit log
    @PostMapping
    public ResponseEntity<HabitLog> createOrUpdateHabitLog(@RequestBody HabitLog habitLog) {
        HabitLog saveHabitLog = habitLogService.saveHabitLog(habitLog);
        return new ResponseEntity<>(saveHabitLog, HttpStatus.CREATED);
    }

    // get all habit logs
    @GetMapping
    public ResponseEntity<List<HabitLog>> getAllHabitLogs() {
        List<HabitLog> habitLogs = habitLogService.getAllHabitLogs();
        return new ResponseEntity<>(habitLogs, HttpStatus.OK);
    }

    // get habit log by id
    @GetMapping("/{id}")
    public ResponseEntity<HabitLog> getHabitLogById(@PathVariable Long id) {
        Optional<HabitLog> habitLog = habitLogService.getHabitLogById(id);
        if(habitLog.isPresent()) {
            return new ResponseEntity<>(habitLog.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get habit logs by Habit ID
    @GetMapping("/habit/{habitId}")
    public ResponseEntity<List<HabitLog>> getHabitLogsByHabitId(@PathVariable Long habitId) {
        List<HabitLog> habitLogs = habitLogService.getHabitLogsByHabitId(habitId);
        return new ResponseEntity<>(habitLogs, HttpStatus.OK);
    }

    // Get habit log by Habit ID and Date
    @GetMapping("/habit/{habitId}/date/{date}")
    public ResponseEntity<HabitLog> getHabitLogByHabitIdAndDate(@PathVariable Long habitId, @PathVariable String date) {
        Optional<HabitLog> habitLog = habitLogService.getHabitLogByHabitIdAndDate(habitId, LocalDate.parse(date));
        if (habitLog.isPresent()) {
            return new ResponseEntity<>(habitLog.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Get habit logs by completion status
    @GetMapping("/status/{completed}")
    public ResponseEntity<List<HabitLog>> getHabitLogsByCompletionStatus(@PathVariable boolean completed) {
        List<HabitLog> habitLogs = habitLogService.getHabitLogsByCompletionStatus(completed);
        return new ResponseEntity<>(habitLogs, HttpStatus.OK);
    }

    // Delete habit log by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabitLog(@PathVariable Long id) {
        boolean isDeleted = habitLogService.deleteHabitLog(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
