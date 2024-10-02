import { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import { Product } from "../../../models/Product";
import productService from "../../../services/ProductService";

export default function ProductSearch()
{
    const [products, setProducts] = useState<Product[]>([]);

    const location = useLocation();
    const params = new URLSearchParams(location.search);
    const name = params.get("name") ?? '';
    const catId = params.get("catId") ?? 0;
    const minPrice = params.get("minPrice") ?? 0;
    const maxPrice = params.get("maxPrice") ?? 0;

    useEffect(() => {loadProducts()}, [])

    async function loadProducts() 
    {
        let prodList = await productService.getAllProducts();

        // Filter By QueryString: name, catId, minPrice, maxPrice
        if (name !== '') prodList = prodList.filter((product: Product) => product.name.toLowerCase() === name.toLowerCase())
        if (+catId !== 0) prodList = prodList.filter((product: Product) => product.categoryId === +catId);
        if (+maxPrice !== 0) prodList = prodList.filter((product: Product) => product.unitPrice <= +maxPrice)
        prodList = prodList.filter((product: Product) => product.unitPrice >= +minPrice)

        setProducts(prodList);    
    }

    return (
        <>
        <h4>Product Search</h4>

        <Link to="/products/add" className="btn btn-outline-success">Add</Link>

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