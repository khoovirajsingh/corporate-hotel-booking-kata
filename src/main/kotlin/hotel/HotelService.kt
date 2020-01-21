package hotel

class HotelService(private val hotelRepository: HotelRepository) {
    fun setRoom(hotelId: Int, roomType: RoomType, quantity: Int) {
        val hotel = hotelRepository.findHotelBy(hotelId) ?: throw HotelDoesNotExist()
    }
}