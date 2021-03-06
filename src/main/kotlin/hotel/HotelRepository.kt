package hotel

class HotelRepository {
    private val hotels = mutableListOf<Hotel>()
    private val rooms = mutableListOf<Room>()

    fun findHotelBy(hotelId: Int): Hotel? {
        return hotels.find { it.id == hotelId }
    }

    fun update(room: Room) {
        rooms.remove(findRoom(room.hotelId, room.roomNumber))
        add(room)
    }

    fun findRoom(hotelId: Int, roomNumber: Int): Room? {
        return rooms.find { it.hotelId == hotelId && it.roomNumber == roomNumber }
    }

    fun add(room: Room) {
        rooms.add(room)
    }

    fun add(hotel: Hotel) {
        hotels.add(hotel)
    }
}
