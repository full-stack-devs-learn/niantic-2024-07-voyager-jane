import categoryService from "../../../services/category-service"
import { useState, useEffect } from "react";
import productService from "../../../services/product-service";

export default function ProductAdd({onCancel, onProductAdded})
{
    const [categories, setCategories] = useState([]);
    const [productName, setProductName] = useState('');
    const [categoryId, setCategoryId] = useState(0);
    const [quantityPerUnit, setQuantityPerUnit] = useState('');
    const [unitPrice, setUnitPrice] = useState('');

    useEffect(() => {
        categoryService.getAllCategories().then(data => {
            setCategories(data);
        });
    }, [])
    
    async function addProductHandler(event)
    {
        event.preventDefault();

        const newProduct = {
            name: productName,
            categoryId: categoryId,
            quantityPerUnit: quantityPerUnit,
            unitPrice: unitPrice
        }

        await productService.addProduct(newProduct);

        onProductAdded();
    }

    return (
        <div className="container mt-4">

            <h1>Add Product</h1>

            <form onSubmit={addProductHandler}>
                <div className="mb-3">
                    <label htmlFor="product-name">Product Name:</label>
                    <input type="text" className="form-control" name="product-name" id="product-name" onChange={(e) => setProductName(e.target.value)}/>
                </div>

                <div className="mb-3">
                    <label htmlFor="category">Choose a Category:</label>
                    <select className="form-select" name="category" id="category" defaultValue={'default'} onChange={(e) => setCategoryId(e.target.value)}>
                        <option value={'default'} disabled>--Pick a Category--</option>
                        {categories.map((category) => (
                            <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                        ))}
                    </select>
                </div>

                <div className="mb-3">
                    <label htmlFor="quantity-per-unit">Quantity Per Unit:</label>
                    <input type="text" className="form-control" name="quantity-per-unit" id="quantity-per-unit" onChange={(e) => setQuantityPerUnit(e.target.value)} />
                </div>

                <div className="mb-3">
                    <label htmlFor="unit-price">Unit Price: </label>
                    <div className="input-group">
                        <span className="input-group-text">$</span>
                        <input type="text" className="form-control" name="unit-price" id="unit-price" onChange={(e) => setUnitPrice(e.target.value)} />
                    </div>
                </div>

                <div>
                    <button className="btn btn-success" type="submit">Save</button>
                    <button className="btn btn-dark" type="cancel" onClick={onCancel}>Cancel</button>
                </div>
            </form>
        </div>
    )
}