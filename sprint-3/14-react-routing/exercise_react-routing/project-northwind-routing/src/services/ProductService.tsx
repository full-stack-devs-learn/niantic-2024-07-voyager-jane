import axios from "axios"
import { Product } from "../models/Product"

export class ProductService 
{
    baseUrl = "http://localhost:8080/products"

    async getAllProducts(): Promise<Product[]>
    {
        const response = await axios.get<Product[]>(this.baseUrl)
        return response.data
    }
}

const productService = new ProductService()
export default productService