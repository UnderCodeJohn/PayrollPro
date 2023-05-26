package com.payrollpro.payrollpro.controller;

import com.payrollpro.payrollpro.Enum.ClockEntryType;
import com.payrollpro.payrollpro.Interface.Clock;
import com.payrollpro.payrollpro.Interface.ViewChangeHelper;
import com.payrollpro.payrollpro.model.ClockEntry;
import com.payrollpro.payrollpro.model.Employee;
import com.payrollpro.payrollpro.model.User;
import com.payrollpro.payrollpro.model.UserSession;
import com.payrollpro.payrollpro.utils.ClockEntryHelper;
import com.payrollpro.payrollpro.utils.EmployeeQuery;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class EmployeeController implements Initializable, ViewChangeHelper {
    public AnchorPane rootPane;
    public VBox containerPane;
    public VBox userPane;
    public Label localTime;

    public User SUser;
    public Button clockInAndOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userPane.getStyleClass().add("main_pane");
        centerView(containerPane);

        Clock clock = (zoneId, timeLabel) -> {
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    LocalDateTime time = LocalDateTime.now(zoneId);
                    String timeString = time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    timeLabel.setText(timeString);
                }
            }.start();
        };

        ZoneId localZoneId = ZoneId.systemDefault();
        clock.displayClock(localZoneId, localTime);

    }

    public void clockInAndOut(ActionEvent actionEvent) throws SQLException {
        SUser = UserSession.getLoggedUser();
        Employee SEmployee = EmployeeQuery.getEmployee(SUser);
        ZoneId localZoneId = ZoneId.systemDefault();
        LocalDateTime time = LocalDateTime.now(localZoneId);

        if (EmployeeQuery.getEmployee(SUser).isClockedIn()) {
            EmployeeQuery.updateEmployeeClockedInStatus(SUser, false);
            showConfirmation("Clock Out", "Clock out successful");
            EmployeeQuery.insertClockedInEntry(new ClockEntry(-1, SEmployee.getEmployeeId(), time, ClockEntryType.CLOCK_OUT));
            List<ClockEntry> entries;
            entries = EmployeeQuery.getClockedEntries(SEmployee);
            System.out.println(ClockEntryHelper.calculateTotalHours(entries));
        } else {
            EmployeeQuery.updateEmployeeClockedInStatus(SUser, true);
            System.out.println("Clocked In");
            showConfirmation("Clock In", "Clock in successful");
            EmployeeQuery.insertClockedInEntry(new ClockEntry(-1, SEmployee.getEmployeeId(), time, ClockEntryType.CLOCK_IN));
        }
    }

    public void viewPayStub(ActionEvent actionEvent) throws SQLException {
        SUser = UserSession.getLoggedUser();
        Employee SEmployee = EmployeeQuery.getEmployee(SUser);
        List<ClockEntry> entries;
        entries = EmployeeQuery.getClockedEntries(SEmployee);
        System.out.println(ClockEntryHelper.calculateTotalHours(entries));
    }

    public void manageAccount(ActionEvent actionEvent) {
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        changeViewAndCenter("login-view.fxml", rootPane);
    }

    private void showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
//        static Map<LocalDate, Long> calculateTotalHours(List<ClockEntry> entries, int employeeId) {
//            Map<LocalDate, Long> totalHoursByDate = new HashMap<>();
//            LocalDateTime previousTimestamp = null;
//            int previousEmployeeId = -1;
//
//            for (ClockEntry entry : entries) {
//                if (entry.getEmployeeId() == employeeId) {
//                    if (entry.getEntryType() == ClockEntryType.CLOCK_IN) {
//                        previousTimestamp = entry.getTimestamp();
//                        previousEmployeeId = employeeId;
//                    } else if (entry.getEntryType() == ClockEntryType.CLOCK_OUT && previousTimestamp != null) {
//                        LocalDateTime currentTimestamp = entry.getTimestamp();
//
//                        if (previousTimestamp.toLocalDate().equals(currentTimestamp.toLocalDate())) {
//                            LocalDate currentDate = previousTimestamp.toLocalDate();
//                            long hours = previousTimestamp.until(currentTimestamp, ChronoUnit.HOURS);
//                            totalHoursByDate.put(currentDate, totalHoursByDate.getOrDefault(currentDate, 0L) + hours);
//                        }
//
//                        previousTimestamp = null;
//                        previousEmployeeId = -1;
//                    }
//                }
//            }
//            return totalHoursByDate;
//        }
}
