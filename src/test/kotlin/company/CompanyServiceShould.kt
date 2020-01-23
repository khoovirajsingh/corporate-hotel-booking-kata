package company

import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.amshove.kluent.shouldThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val EMPLOYEE_ID = 1234
private const val COMPANY_ID = 1234
private val EMPLOYEE = Employee(EMPLOYEE_ID, COMPANY_ID)

class CompanyServiceShould {
    private lateinit var companyRepository: CompanyRepository
    private lateinit var companyService: CompanyService

    @BeforeEach
    fun setUp() {
        companyRepository = spyk()
        companyService = CompanyService(companyRepository)
    }

    @Test
    fun `add an employee`() {
        companyService.addEmployee(COMPANY_ID, EMPLOYEE_ID)
        verify { companyRepository.add(EMPLOYEE) }
    }

    @Test
    fun `not allow adding same employee twice`() {
        every { companyRepository.find(EMPLOYEE_ID) } returns EMPLOYEE

        val addEmployee = { companyService.addEmployee(COMPANY_ID, EMPLOYEE_ID) }

        addEmployee shouldThrow EmployeeAlreadyExist::class
    }
}