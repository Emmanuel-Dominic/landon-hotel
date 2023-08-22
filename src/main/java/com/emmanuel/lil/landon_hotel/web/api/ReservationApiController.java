package com.emmanuel.lil.landon_hotel.web.api;

import com.emmanuel.lil.landon_hotel.data.entity.Reservation;
import com.emmanuel.lil.landon_hotel.data.repository.ReservationRepository;
import com.emmanuel.lil.landon_hotel.web.exception.BadRequestException;
import com.emmanuel.lil.landon_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
    private final ReservationRepository reservationRepository;

    public ReservationApiController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation getReservation(@PathVariable("id") long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new NotFoundException("Reservation not found");
        }
        return reservation.get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Reservation updateReservation(@PathVariable("id") long id, @RequestBody Reservation reservation) {
        if(id != reservation.getId()){
            throw new BadRequestException("id mismatch");
        }
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable("id") long id) {
        reservationRepository.deleteById(id);
    }
}
