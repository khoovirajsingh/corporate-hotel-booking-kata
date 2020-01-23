package hotel

import hotel.RoomType.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.amshove.kluent.`should be`
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal const val HOTEL_ID = 7
internal const val HOTEL_NAME = "Hilton"
internal val HOTEL = Hotel(HOTEL_ID, HOTEL_NAME)
internal const val ROOM_NUMBER = 6
internal val STANDARD_ROOM = Room(HOTEL_ID, ROOM_NUMBER, STANDARD)
internal val MASTER_ROOM = Room(HOTEL_ID, ROOM_NUMBER, MASTER_SUITE)

class HotelServiceShould {


    private lateinit var hotelService: HotelService
    private lateinit var hotelRepository: HotelRepository

    @BeforeEach
    fun setUp() {
        hotelRepository = spyk()
        hotelService = HotelService(hotelRepository)
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns HOTEL
    }

    @Test
    fun `throw an exception when adding an existing hotel`() {
        val addHotel = { hotelService.addHotel(HOTEL_ID, HOTEL_NAME) }

        addHotel shouldThrow HotelAlreadyExist::class
    }

    @Test
    fun `add a new hotel`() {
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns null

        hotelService.addHotel(HOTEL_ID, HOTEL_NAME)

        verify { hotelRepository.add(HOTEL) }
    }

    @Test
    fun `throw an exception when adding a room to a non existing hotel`() {
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns null

        val setRoom = { hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, STANDARD) }

        setRoom shouldThrow HotelDoesNotExist::class
    }

    @Test
    fun `update a room`() {
        every { hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER) } returns STANDARD_ROOM

        hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, STANDARD)

        verify { hotelRepository.update(STANDARD_ROOM) }
    }

    @Test
    fun `add a room`() {
        every { hotelRepository.findRoom(HOTEL_ID, ROOM_NUMBER) } returns null

        hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, MASTER_SUITE)

        verify { hotelRepository.add(MASTER_ROOM) }
    }

    @Test
    fun `find hotel by id`() {
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns HOTEL

        val hotel = hotelService.findHotelById(HOTEL_ID)

        hotel `should be` HOTEL
    }
}