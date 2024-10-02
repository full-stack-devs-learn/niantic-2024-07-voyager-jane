import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Category } from "../../../models/Category";
import categoryService from "../../../services/CategoryService";

export default function CategoriesList()
{
    const [categories, setCategories] = useState<Category[]>([]);

    useEffect(() => {
        loadCategories()
    }, [])

    async function loadCategories()
    {
        try
        {
            const list = await categoryService.getAllCategories();
            setCategories(list);
        }
        catch(error)
        {
            console.log(error)
        }
    }

    return (
        <>
            <h4>Categories List</h4>

            <Link to="/categories/add" className="btn btn-outline-success">Add</Link>

            <table className="table table-striped mt-4">
                <thead className="table table-dark">
                <tr>
                    <th>Category</th>
                    <td></td>
                </tr>
                </thead>
                <tbody>
                {
                    categories.map((category: Category) => (
                        <tr key={category.categoryId}>
                            <th><Link to={`/categories/${category.categoryId}`}>{category.categoryName}</Link></th>
                            <td style={{textAlign: 'right'}}><Link to={`/categories/${category.categoryId}/edit`} className="btn btn-outline-light btn-sm">Edit</Link></td>
                        </tr>
                    ))
                }
                </tbody>
            </table>
        </>
    )
}