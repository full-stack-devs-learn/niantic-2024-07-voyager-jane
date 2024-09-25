import './EmployeesRow.css'
import { useState } from 'react';
import EmployeeDetails from '../../employee-details/EmployeeDetails';

export default function EmployeesRow({fullName, title, id, salary, notes})
{
    const imageUrl = `images/employees/${id}.webp`

    const [details, shareDetails] = useState('');

    const changePage = (change) => {
        shareDetails(change);
    }

    return (
        <>
        {(details === '') &&
            <div className="employee-row" onClick={() => changePage("show")}>
            <img id="employee-image" src={imageUrl} />
            <div className="employee-name">
                <h2>{fullName}</h2>
                <h6>{"Employee Id: " + id}</h6>
                <h6>{title}</h6>
            </div>
            </div>
        }

        {(details === 'show') && <EmployeeDetails key={id}
                        fullName={fullName}
                        title={title}
                        id={id}
                        salary={salary}
                        notes={notes}
                        onPageChanged={changePage}></EmployeeDetails>
        }
        </>
    )
}