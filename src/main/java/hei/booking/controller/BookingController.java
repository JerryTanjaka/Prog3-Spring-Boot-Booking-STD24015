package hei.booking.controller;

import hei.booking.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    private List<Booking> bookingList = new ArrayList<>();

    @GetMapping("/booking")
    public List<Booking> booking() {
        return bookingList;
    }

    @PostMapping("/booking")
    public ResponseEntity<List<Booking>> addBooking(@RequestBody List<Booking> bookings){

        Booking invalid = bookings.stream()
                .filter(b -> b.getRoomNumber() < 1 || b.getRoomNumber() > 9)
                .findFirst()
                .orElse(null);

        if (invalid != null) {
            return ResponseEntity.badRequest().build();
        }

        Booking conflict = bookings.stream()
                .filter(b -> bookingList.stream()
                        .anyMatch(e -> e.isBooked(b.getDate(), b.getRoomNumber())))
                .findFirst()
                .orElse(null);

        if (conflict != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        bookingList.addAll(bookings);

        return ResponseEntity.ok(bookings);
    }
}