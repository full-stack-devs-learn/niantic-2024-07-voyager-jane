import { Link } from "react-router-dom";

export default function ProductSearch()
{
    return (
        <>
        <h4>Product Search</h4>

        <Link to="/products/add" className="btn btn-outline-success">Add</Link>

            <table>
                <tr>
                    <th>Product</th>
                    <td></td>
                </tr>
                <tr>
                    <th><Link to="/products/2">Product 2</Link></th>
                    <td><Link to="/products/2/edit" className="btn btn-outline-light btn-sm">Edit</Link></td>
                </tr>
            </table>
        </>
    )
}