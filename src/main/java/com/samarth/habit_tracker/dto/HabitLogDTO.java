package com.samarth.habit_tracker.dto;
import com.samarth.habit_tracker.model.Habit;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class HabitLogDTO {
    private Habit habit;
    private LocalDate date;
    private boolean completed;
    private String notes;
}
