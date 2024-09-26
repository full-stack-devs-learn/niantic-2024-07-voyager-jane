import { useEffect, useState } from 'react';
import './ProductsList.css'
import productService from '../../../services/product-service';

export default function ProductsList({categoryId})
{
    // call the products api - getProductsByCategoryId
    const [products, setProducts] = useState([]);

    useEffect(() => {
        if (categoryId !== 0)
        productService.getProductByCategoryId(categoryId).then(data => {
            setProducts(data);
        })
    }, []) 

    return (
        <>
        {(categoryId == 0) 
        ? <div>No Category Selected</div>
        : <h3>
            Products for category: {categoryId}
          </h3>
        }

        <ul>
            {(categoryId !== 0) &&
                 products.map((product) => (
                    <li key={product.id}>{product.name}</li>
                )) }
        </ul>
        </>
    )
}