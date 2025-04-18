package com.samarth.habit_tracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitDTO {
    private String name;
    private String description;
    private int goal;
    private String frequency;
    private boolean status;
}
