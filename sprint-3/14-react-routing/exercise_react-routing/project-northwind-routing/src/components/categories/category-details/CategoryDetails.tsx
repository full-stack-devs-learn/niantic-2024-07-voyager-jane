import { useEffect, useState } from "react"
import { Category } from "../../../models/Category";
import { useParams } from "react-router-dom";
import categoryService from "../../../services/CategoryService";

export default function CategoryDetails()
{
    const [category, setCategory] = useState<Category>();

    const params = useParams();
    const catId = params.catId ?? 0;

    useEffect(() => {loadCategory()}, [])

    async function loadCategory() 
    {
        const getCat = await categoryService.getCategoryById(+catId)
        setCategory(getCat);
    }

    return (
        <>
            <h6>Category Details</h6>
            <h4>{category?.categoryName}</h4>
            <p>{category?.description}</p>
        </>
    )
}