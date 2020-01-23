package company

class CompanyService(private val companyRepository: CompanyRepository) {

    fun addEmployee(companyId: Int, employeeId: Int) {
        if (companyRepository.find(employeeId) != null) {
            throw EmployeeAlreadyExist()
        } else {
            companyRepository.add(Employee(companyId, employeeId))
        }
    }

    fun deleteEmployee(employeeId: Int) {

    }
}