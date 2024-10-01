import { Link } from "react-router-dom";

export default function CategoriesList()
{
    return (
        <>
            <h4>Categories List</h4>

            <Link to="/categories/add" className="btn btn-outline-success">Add</Link>
            <Link to="/categories/edit" className="btn btn-outline-light">Edit</Link>

            <ul>
                <li><Link to="/categories/2">Category 2</Link></li>
            </ul>
        </>
    )
}