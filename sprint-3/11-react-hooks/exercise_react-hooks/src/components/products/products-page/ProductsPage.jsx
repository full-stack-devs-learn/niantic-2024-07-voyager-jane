import ProductAdd from '../product-add/ProductAdd'
import ProductCardContainer from '../product-card-container/ProductCardContainer'
import './ProductsPage.css'

export default function ProductsPage()
{
    return (
        <>
        <header className="container mt-4">
            <h1>Products</h1>
        </header>
        <ProductCardContainer></ProductCardContainer>
        <ProductAdd></ProductAdd>
        </>
    )
}