import { XCircleFill } from "react-bootstrap-icons"
import productService from "../../../services/product-service";

export default function ProductCard({product, id, onCategoryDeleted})
{
    async function deleteCategory(event)
    {
        event.stopPropagation();

        await productService.deleteProduct(id);

        onCategoryDeleted(id);
    }

    return (
        <div className="card category-card">
            <div id="category-header" className="card-header">{product}</div>
            <div id="category-body" className="card-body">

            </div>
            <div className="card-footer"><XCircleFill color="red" onClick={deleteCategory}></XCircleFill></div>
        </div>
    )
}