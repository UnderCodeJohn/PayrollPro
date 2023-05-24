package com.payrollpro.payrollpro.model;

import java.time.LocalDateTime;

public class ClockEntry {
    private LocalDateTime timestamp;
    private ClockEntry entryType;

    public ClockEntry(LocalDateTime timestamp, ClockEntry entryType) {
        this.timestamp = timestamp;
        this.entryType = entryType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ClockEntry getEntryType() {
        return entryType;
    }

    public void setEntryType(ClockEntry entryType) {
        this.entryType = entryType;
    }
}
