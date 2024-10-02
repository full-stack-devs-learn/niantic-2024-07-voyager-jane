import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Product } from "../../../models/Product";
import productService from "../../../services/ProductService";

export default function ProductSearch()
{
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {loadProducts()}, [])

    async function loadProducts() 
    {
        const prodList = await productService.getAllProducts();
        setProducts(prodList);    
    }

    return (
        <>
        <h4>Product Search</h4>

        <Link to="/products/add" className="btn btn-outline-success">Add</Link>

        <form>
            <h4>Filters</h4>
            <div>
                <label htmlFor="product-name">Product Name</label>
                <input type="search" name="product-name" id="product-name" />
                <input type="submit" value="Search" />
            </div>    
        </form>

        <table className="table mt-4">
            <thead className="table table-dark">
            <tr>
                <th>Product</th>
                <th>Category</th>
                <td></td>
            </tr>
            </thead>
            <tbody>
                {
                    products.map((product: Product) => (
                        <tr key={product.id}>
                            <th><Link to={`/products/${product.id}`}>{product.name}</Link></th>
                            <td>{product.categoryId}</td>
                            <td style={{textAlign: 'right'}}><Link to={`/products/${product.id}/edit`} className="btn btn-outline-light btn-sm">Edit</Link></td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        </>
    )
}