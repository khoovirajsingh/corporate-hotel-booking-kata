package hotel

class HotelService(private val hotelRepository: HotelRepository) {
    fun setRoom(hotelId: Int, roomNumber: Int, roomType: RoomType) {
        val hotel = hotelRepository.findHotelBy(hotelId) ?: throw HotelDoesNotExist()
        val room = Room(hotelId, roomNumber, roomType)
        if (hotelRepository.findRoom(hotelId, roomNumber) != null) {
            hotelRepository.update(room)
        }
    }
}