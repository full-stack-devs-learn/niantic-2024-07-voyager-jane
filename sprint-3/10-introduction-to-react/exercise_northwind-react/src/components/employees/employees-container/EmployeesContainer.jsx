import './EmployeesContainer.css'
import EmployeesRow from '../employees-row/EmployeesRow'

export default function EmployeesContainer()
{
    return (
        <main className="container mt-4 employees-container" id="employees-container">

            <EmployeesRow></EmployeesRow>

        </main>
    )
}