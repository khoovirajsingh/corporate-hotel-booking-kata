package company

class CompanyService(private val companyRepository: CompanyRepository) {

    fun addEmployee(companyId: Int, employeeId: Int) {
        if (doesEmployeeExist(employeeId)) {
            throw EmployeeAlreadyExist()
        } else {
            companyRepository.add(Employee(companyId, employeeId))
        }
    }

    private fun doesEmployeeExist(employeeId: Int) = companyRepository.find(employeeId) != null

    fun deleteEmployee(employeeId: Int) {

    }
}