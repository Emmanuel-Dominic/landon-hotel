package com.emmanuel.lil.landon_hotel.web.api;
import com.emmanuel.lil.landon_hotel.data.entity.Guest;
import com.emmanuel.lil.landon_hotel.data.repository.GuestRepository;
import com.emmanuel.lil.landon_hotel.web.exception.BadRequestException;
import com.emmanuel.lil.landon_hotel.web.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guests")
public class GuestApiController {
    private final GuestRepository guestRepository;
    public GuestApiController(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addGuest(@RequestBody Guest guest) {
        return guestRepository.save(guest);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Guest getGuest(@PathVariable("id") long id) {
        Optional<Guest> guest = guestRepository.findById(id);
        if(guest.isEmpty()){
            throw new NotFoundException("Guest not found");
        }
        return guest.get();
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Guest updateGuest(@PathVariable("id") long id, @RequestBody Guest guest) {
        if(id != guest.getId()){
            throw new BadRequestException("id mismatch");
        }
        return guestRepository.save(guest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuest(@PathVariable("id") long id) {
        guestRepository.deleteById(id);
    }
}
