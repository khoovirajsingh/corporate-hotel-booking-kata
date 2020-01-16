package features

import company.CompanyService
import employee.BookingService
import hotel.HotelService
import hotel.RoomType.STANDARD
import org.amshove.kluent.`should not be`
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

class EmployeeBookingFeature {
    companion object {
        private const val EMPLOYEE_ID = 13
        private const val HOTEL_ID = 7
        private const val COMPANY_ID = 27
        private const val QUANTITY = 6
        private val ROOM_TYPE = STANDARD
        private val CHECKIN = LocalDate.of(2020, 1, 16)
        private val CHECKOUT = LocalDate.of(2020, 1, 19)
    }

    private lateinit var hotelService: HotelService
    private lateinit var companyService: CompanyService
    private lateinit var bookingService: BookingService

    @BeforeEach
    fun setUp() {
        hotelService = HotelService()
        companyService = CompanyService()
        bookingService = BookingService()
    }

    @Test
    fun `an employee can book a room`() {
        hotelService.setRoomType(HOTEL_ID, ROOM_TYPE, QUANTITY)
        companyService.addEmployee(COMPANY_ID, EMPLOYEE_ID)

        val booking = bookingService.book(EMPLOYEE_ID, HOTEL_ID, ROOM_TYPE, CHECKIN, CHECKOUT)

        booking `should not be` null
    }
}