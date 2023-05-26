package com.payrollpro.payrollpro.utils;

import com.payrollpro.payrollpro.Enum.ClockEntryType;
import com.payrollpro.payrollpro.model.ClockEntry;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClockEntryHelper {
    public record TotalHoursPerDay(LocalDate date, int employeeId, long totalHours) {
    }

    public static List<TotalHoursPerDay> calculateTotalHours(List<ClockEntry> entries) {
        List<TotalHoursPerDay> totalHoursList = new ArrayList<>();
        Map<LocalDate, Map<Integer, Long>> hoursByDateAndEmployee = new HashMap<>();

        for (ClockEntry entry : entries) {
            if (entry.getEntryType() == ClockEntryType.CLOCK_IN) {
                LocalDateTime clockInTimestamp = entry.getTimestamp();
                int employeeId = entry.getEmployeeId();
                LocalDate currentDate = clockInTimestamp.toLocalDate();

                // Check if the current combination of date and employeeId exists in the map
                if (!hoursByDateAndEmployee.containsKey(currentDate)) {
                    hoursByDateAndEmployee.put(currentDate, new HashMap<>());
                }

                // Get the map of employeeId and hours for the current date
                Map<Integer, Long> hoursByEmployee = hoursByDateAndEmployee.get(currentDate);

                // Calculate the total hours for the current employeeId and update the map
                long totalHours = hoursByEmployee.getOrDefault(employeeId, 0L);
                totalHours += calculateHoursForEmployee(entries, employeeId, currentDate);
                hoursByEmployee.put(employeeId, totalHours);
            }
        }

        // Generate the TotalHoursPerDay objects for each unique combination of date and employeeId
        for (Map.Entry<LocalDate, Map<Integer, Long>> entry : hoursByDateAndEmployee.entrySet()) {
            LocalDate date = entry.getKey();
            Map<Integer, Long> hoursByEmployee = entry.getValue();

            for (Map.Entry<Integer, Long> employeeEntry : hoursByEmployee.entrySet()) {
                int employeeId = employeeEntry.getKey();
                long totalHours = employeeEntry.getValue();

                totalHoursList.add(new TotalHoursPerDay(date, employeeId, totalHours));
            }
        }

        return totalHoursList;
    }

    private static long calculateHoursForEmployee(List<ClockEntry> entries, int employeeId, LocalDate date) {
        LocalDateTime previousTimestamp = null;
        long totalHours = 0;

        for (ClockEntry entry : entries) {
            if (entry.getEmployeeId() == employeeId && entry.getEntryType() == ClockEntryType.CLOCK_OUT) {
                LocalDateTime currentTimestamp = entry.getTimestamp();

                if (currentTimestamp.toLocalDate().equals(date)) {
                    long hours = previousTimestamp.until(currentTimestamp, ChronoUnit.HOURS);
                    totalHours += hours;
                }
            } else if (entry.getEmployeeId() == employeeId && entry.getEntryType() == ClockEntryType.CLOCK_IN) {
                previousTimestamp = entry.getTimestamp();
            }
        }

        return totalHours;
    }




}

