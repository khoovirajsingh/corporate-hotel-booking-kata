package hotel

import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HotelServiceShould {
    companion object {
        private const val HOTEL_ID = 7
        private const val QUANTITY = 6
        private val ROOM_TYPE = RoomType.STANDARD
    }

    private lateinit var hotelService: HotelService
    private lateinit var hotelRepository: HotelRepository

    @BeforeEach
    fun setUp() {
        hotelRepository = mockk()
        hotelService = HotelService(hotelRepository)
    }

    @Test
    fun `throw an exception if hotel does not exist`() {
        every { hotelRepository.findHotelBy(HOTEL_ID) } returns null
        val setRoom = { hotelService.setRoom(HOTEL_ID, ROOM_TYPE, QUANTITY) }
        setRoom shouldThrow HotelDoesNotExist::class
    }
}