package employee

import hotel.RoomType
import java.time.LocalDate

data class Booking(
    val bookingId: Int,
    val employeeId: Int,
    val hotelId: Int,
    val roomType: RoomType,
    val checkin: LocalDate,
    val checkout: LocalDate
)
