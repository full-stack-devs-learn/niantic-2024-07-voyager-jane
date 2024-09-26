import { useState } from 'react'
import ProductAdd from '../product-add/ProductAdd'
import ProductCardContainer from '../product-card-container/ProductCardContainer'
import './ProductsPage.css'

export default function ProductsPage()
{
    const [action, setPAction] = useState("list");

    return (
        <>
        <header className={`container mt-4 ${action === "add" ? "d-none" : ''}`}>
            <h1>Products</h1>
            <button className="btn btn-success" onClick={() => setPAction("add")}>Add</button>
        </header>

        {(action === 'list') && <ProductCardContainer></ProductCardContainer>}
        {(action === 'add') && <ProductAdd 
                onCancel={() => setPAction("list")}
                onProductAdded={() => setPAction("list")}></ProductAdd>}
        
        </>
    )
}