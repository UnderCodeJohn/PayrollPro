package com.payrollpro.payrollpro.Interface;

import javafx.scene.control.Label;

import java.time.ZoneId;

public interface Clock {

        /**

         Displays the clock with the specified time zone on the given label.
         @param localZoneId the local time zone to display the clock in
         @param timeLabel the label to display the clock on
         */
        void displayClock(ZoneId localZoneId, Label timeLabel);
}

