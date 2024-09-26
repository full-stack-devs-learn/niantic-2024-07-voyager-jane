import categoryService from "../../../services/category-service"
import { useState, useEffect } from "react";

export default function ProductAdd()
{
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        categoryService.getAllCategories().then(data => {
            setCategories(data);
        });
    }, [])
    

    return (
        <div className="container">

            <h2>Add Product</h2>

            <form>
                <div className="row">
                    <label htmlFor="product-name">Product Name:</label>
                    <input type="text" className="form-control" name="product-name" id="product-name"/>
                </div>

                <div className="row">
                    <label htmlFor="category">Choose a Category:</label>
                    <select name="category" id="category">
                        {categories.map((category) => (
                            <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                        ))}
                    </select>
                </div>

                <div className="row">
                    <label htmlFor="quantity-per-unit">Quantity Per Unit:</label>
                    <input type="text" className="form-control" name="quantity-per-unit" id="quantity-per-unit" />
                </div>

                <div className="row">
                    <label htmlFor="unit-price">Unit Price: </label>
                    <input type="text" name="unit-price" id="unit-price" />
                </div>
            </form>
        </div>
    )
}