package hotel

import org.amshove.kluent.`should be`
import org.junit.jupiter.api.Test

class HotelRepositoryShould {
    @Test
    fun `find a hotel by its id`() {
        val hotelRepository = HotelRepository()
        hotelRepository.add(HOTEL)
        val hotel = hotelRepository.findHotelBy(HOTEL_ID)
        hotel `should be` HOTEL
    }
}