import productService from "../../../services/product-service";
import ProductCard from "../product-card/ProductCard";
import { useState, useEffect } from "react";

export default function ProductCardContainer()
{
    const [products, setProducts] = useState([]);

    useEffect(() => {
        productService.getAllProducts().then(data => {
            setProducts(data);
        })
    }, [])

    return (
        <main className="container mt-4 categories-container">
            {
                products.map((product) => (
                    <ProductCard key={product.id}
                        product={product.name}
                        id={product.id}>
                    </ProductCard>
                ))
            }
            
        </main>
    )  
}