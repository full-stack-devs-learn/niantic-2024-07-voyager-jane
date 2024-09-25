import './EmployeesContainer.css'
import EmployeesRow from '../employees-row/EmployeesRow'
import { employees } from '../../../data'

export default function EmployeesContainer()
{

    // const [details, shareDetails] = useState('');

    // const changePage = (change) => {
    //     shareDetails(change);
    // }


    return (
        <main className="container mt-4 employees-container" id="employees-container">
        {/* {(details === 'show') && <EmployeeDetails key={employee.employeeId}
                        fullName={employee.firstName + " " + employee.lastName}
                        title={employee.title}
                        id={employee.employeeId}
                        salary={employee.salary}
                        notes={employee.notes}
                        onPageChanged={changePage}></EmployeeDetails>
        } */}
        {
            employees.map((employee) => (
                <EmployeesRow key={employee.employeeId}
                            fullName={employee.firstName + " " + employee.lastName}
                            title={employee.title}
                            id={employee.employeeId}
                            salary={employee.salary}
                            notes={employee.notes}
                        ></EmployeesRow>
            ))
        }
        </main>
    )
}