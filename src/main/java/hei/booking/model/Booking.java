package hei.booking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class Booking {
    private String name;
    private String phoneNumber;
    private String email;
    private int roomNumber;
    private LocalDate date;

    public Boolean isBooked(LocalDate date, int roomNumber) {
        return this.date.equals(date) && this.roomNumber == roomNumber;

    }
}