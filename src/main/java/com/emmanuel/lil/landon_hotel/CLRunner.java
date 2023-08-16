package com.emmanuel.lil.landon_hotel;
import com.emmanuel.lil.landon_hotel.data.entity.Guest;
import com.emmanuel.lil.landon_hotel.data.entity.Reservation;
import com.emmanuel.lil.landon_hotel.data.entity.Room;
import com.emmanuel.lil.landon_hotel.data.repository.GuestRepository;
import com.emmanuel.lil.landon_hotel.data.repository.ReservationRepository;
import com.emmanuel.lil.landon_hotel.data.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CLRunner implements CommandLineRunner {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public CLRunner(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("*** GUESTS ***");
        List<Guest> guests = this.guestRepository.findAll();
        guests.forEach(System.out::println);
        System.out.println("*** ROOMS ***");
        List<Room> rooms = this.roomRepository.findAll();
        rooms.forEach(System.out::println);
        System.out.println("*** RESERVATIONS ***");
        List<Reservation> reservations = this.reservationRepository.findAll();
        reservations.forEach(System.out::println);
    }
}
