package com.emmanuel.lil.landon_hotel;
import com.emmanuel.lil.landon_hotel.data.repository.GuestRepository;
import com.emmanuel.lil.landon_hotel.data.repository.ReservationRepository;
import com.emmanuel.lil.landon_hotel.data.repository.RoomRepository;
import com.emmanuel.lil.landon_hotel.service.RoomReservationService;
import com.emmanuel.lil.landon_hotel.service.model.RoomReservation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CLRunner implements CommandLineRunner {
    private final RoomReservationService roomReservationService;

    public CLRunner(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository, RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<RoomReservation> reservations = this.roomReservationService.getRoomReservationForDate("2022-08-18");
    }
}
