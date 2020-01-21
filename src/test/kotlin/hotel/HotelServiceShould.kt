package hotel

import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HotelServiceShould {
    companion object {
        private const val HOTEL_ID = 7
        private const val HOTEL_NAME = "Hilton"
        private const val ROOM_NUMBER = 6
        private val ROOM_TYPE = RoomType.STANDARD
        private val STANDARD_ROOM = Room(HOTEL_ID, this.ROOM_NUMBER, ROOM_TYPE)
    }

    private lateinit var hotelService: HotelService
    private lateinit var hotelRepository: HotelRepository

    @BeforeEach
    fun setUp() {
        hotelRepository = spyk()
        hotelService = HotelService(hotelRepository)
    }

    @Test
    fun `throw an exception if hotel does not exist`() {
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns null
        val setRoom = { hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, ROOM_TYPE) }
        setRoom shouldThrow HotelDoesNotExist::class
    }

    @Test
    internal fun `update a room`() {
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns Hotel(HOTEL_ID, HOTEL_NAME)

        hotelService.setRoom(HOTEL_ID, ROOM_NUMBER, ROOM_TYPE)

        verify { hotelRepository.update(STANDARD_ROOM) }
    }
}