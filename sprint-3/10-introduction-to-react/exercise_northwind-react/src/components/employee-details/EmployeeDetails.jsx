import './EmployeeDetails.css'

export default function EmployeeDetails({fullName, id, title, salary, notes, onPageChanged})
{
    const imageUrl = `images/employees/${id}.webp`

    return (
        <>
        <div id="details-container">
            <div id="employee-head">
                <img width="200px" height="200px" src={imageUrl} />

                <div id="head-text">
                    <h1>{fullName}</h1>
                    <h6>{"Employee Id: " + id}</h6>

                    <table className="table">
                        <thead className="table-dark">
                            <tr>
                                <th>Title</th>
                                <th>Salary</th>
                                <th>Notes</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th>{title}</th>
                                <td>{salary}</td>
                                <td>{notes}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>

            </div>
            <button onClick={() => onPageChanged('')}>Close</button>
        </div>
        </>
    )
}