import { Link } from "react-router-dom";

export default function CategoriesList()
{
    return (
        <>
            <h4>Categories List</h4>

            <Link to="/categories/add" className="btn btn-outline-success">Add</Link>

            <table>
                <tr>
                    <th>Category</th>
                    <td></td>
                </tr>
                <tr>
                    <th><Link to="/categories/2">Category 2</Link></th>
                    <td><Link to="/categories/2/edit" className="btn btn-outline-light btn-sm">Edit</Link></td>
                </tr>
            </table>
        </>
    )
}