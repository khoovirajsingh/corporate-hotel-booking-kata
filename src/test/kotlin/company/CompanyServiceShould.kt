package company

import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

private const val EMPLOYEE_ID = 1234
private const val COMPANY_ID = 234

class CompanyServiceShould {
    private lateinit var companyRepository: CompanyRepository
    private lateinit var companyService: CompanyService

    @BeforeEach
    internal fun setUp() {
        companyRepository = spyk()
        companyService = CompanyService(companyRepository)
    }

    @Test
    fun `add an employee`() {
        val employee = Employee(EMPLOYEE_ID, COMPANY_ID)
        companyService.addEmployee(EMPLOYEE_ID, COMPANY_ID)
        verify { companyRepository.add(employee) }
    }
}