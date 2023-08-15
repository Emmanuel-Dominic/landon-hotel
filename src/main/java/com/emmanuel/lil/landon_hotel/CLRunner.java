package com.emmanuel.lil.landon_hotel;
import com.emmanuel.lil.landon_hotel.data.entity.Room;
import com.emmanuel.lil.landon_hotel.data.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class CLRunner implements CommandLineRunner {
    private final RoomRepository roomRepository;

    public CLRunner(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Room> rooms = roomRepository.findAll();
        Optional<Room> room = this.roomRepository.findByRoomNumberIgnoreCase("p1"); // rooms.get(0).getId());
        System.out.println(room);
        rooms.forEach(System.out::println);
    }
}
