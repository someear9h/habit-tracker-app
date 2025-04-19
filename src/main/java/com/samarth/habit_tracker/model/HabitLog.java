package com.samarth.habit_tracker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "habit_logs")
@Getter // Lombok will generate getters for all fields
@Setter // Lombok will generate setters for all fields
@NoArgsConstructor // Lombok will generate a no-argument constructor
@AllArgsConstructor // Lombok will generate a constructor with all fields as parameters
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // each log belongs to one habit
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean completed;

    private String notes;
}
