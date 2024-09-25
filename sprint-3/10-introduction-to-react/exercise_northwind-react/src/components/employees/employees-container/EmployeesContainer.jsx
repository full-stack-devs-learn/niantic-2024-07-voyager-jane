import './EmployeesContainer.css'
import EmployeesRow from '../employees-row/EmployeesRow'
import { employees } from '../../../data'

export default function EmployeesContainer()
{
    return (
        <main className="container mt-4 employees-container" id="employees-container">
        {
            employees.map((employee) => (
                <EmployeesRow key={employee.employeeId}
                            fullName={employee.firstName + " " + employee.lastName}
                            title={employee.title}
                            id={employee.employeeId}></EmployeesRow>
            ))
        }
        </main>
    )
}