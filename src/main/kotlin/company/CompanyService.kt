package company

class CompanyService(private val companyRepository: CompanyRepository) {

    fun addEmployee(companyId: Int, employeeId: Int) {
        companyRepository.add(Employee(companyId, employeeId))
    }

    fun deleteEmployee(employeeId: Int) {

    }
}