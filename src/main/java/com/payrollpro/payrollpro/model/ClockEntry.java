package com.payrollpro.payrollpro.model;

import com.payrollpro.payrollpro.Enum.ClockEntryType;

import java.time.LocalDateTime;

public class ClockEntry {
    private  int clockedId;
    private int employeeId;
    private LocalDateTime timestamp;
    private ClockEntryType entryType;

    public ClockEntry(int clockedId, int employeeId, LocalDateTime timestamp, ClockEntryType entryType) {
        this.clockedId = clockedId;
        this.employeeId = employeeId;
        this.timestamp = timestamp;
        this.entryType = entryType;
    }

    public int getClockedId() {
        return clockedId;
    }

    public void setClockedId(int clockedId) {
        this.clockedId = clockedId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ClockEntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(ClockEntryType entryType) {
        this.entryType = entryType;
    }
}
