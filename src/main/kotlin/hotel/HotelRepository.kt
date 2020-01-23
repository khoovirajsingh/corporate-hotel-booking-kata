package hotel

class HotelRepository {
    private val hotels = mutableListOf<Hotel>()

    fun findHotelBy(hotelId: Int): Hotel? {
        return hotels.find { it.id == hotelId }
    }

    fun update(standardRoom: Room) {
    }

    fun findRoom(hotelId: Int, roomNumber: Int): Room? {
        return null
    }

    fun add(room: Room) {

    }

    fun add(hotel: Hotel) {
        hotels.add(hotel)
    }
}
