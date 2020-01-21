package hotel

class HotelService(private val hotelRepository: HotelRepository) {
    fun setRoom(hotelId: Int, roomNumber: Int, roomType: RoomType) {
        validateHotelId(hotelId)
        val room = Room(hotelId, roomNumber, roomType)
        if (roomExists(hotelId, roomNumber)) {
            hotelRepository.update(room)
        } else {
            hotelRepository.add(room)
        }
    }

    private fun roomExists(hotelId: Int, roomNumber: Int) =
        hotelRepository.findRoom(hotelId, roomNumber) != null

    private fun validateHotelId(hotelId: Int) {
        hotelRepository.findHotelBy(hotelId) ?: throw HotelDoesNotExist()
    }

    fun addHotel(hotelId: Int, hotelName: String) {
        throw HotelAlreadyExist()
    }
}