package hotel

import hotel.RoomType.*
import org.amshove.kluent.`should be`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HotelRepositoryShould {
    private lateinit var hotelRepository: HotelRepository

    @BeforeEach
    fun setUp() {
        hotelRepository = HotelRepository()
        hotelRepository.add(HOTEL)
        hotelRepository.add(STANDARD_ROOM)
    }

    @Test
    fun `find a hotel by its id`() {
        val hotel = hotelRepository.findHotelBy(HOTEL_ID)
        hotel `should be` HOTEL
    }

    @Test
    fun `find a room by its hotel id and room number`() {
        val room = hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER)
        room `should be` STANDARD_ROOM
    }

    @Test
    fun `update a room`() {
        val masterRoom = Room(HOTEL_ID, ROOM_NUMBER, MASTER_SUITE)
        hotelRepository.update(masterRoom)
        val updatedRoom = hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER)
        updatedRoom?.roomType `should be` MASTER_SUITE
    }
}