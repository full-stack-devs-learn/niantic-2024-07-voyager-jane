import { useEffect, useState } from "react"
import { Product } from "../../../models/Product"
import { useParams } from "react-router-dom";
import productService from "../../../services/ProductService";

export default function ProductDetails()
{
    const [product, setProduct] = useState<Product>();

    const params = useParams();
    const prodId = params.prodId ?? 0;

    useEffect(() => {loadProduct()}, [])

    async function loadProduct() 
    {
        const getProduct = await productService.getProductById(+prodId)
        setProduct(getProduct)
    }

    return (
        <>
        <h6>Product Details</h6>
        <h4>{product?.name}</h4>
        <p>Id: {product?.id}</p>
        <p>Category: {product?.categoryId}</p>
        <p>Unit Price: {product?.unitPrice}</p>
        <p>Quantity Per Unit: {product?.quantityPerUnit}</p>
        </>
    )
}