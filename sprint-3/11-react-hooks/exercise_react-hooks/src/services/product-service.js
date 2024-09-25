import axios from 'axios'

class ProductService
{
    baseUrl = 'http://localhost:8080/products'

    async getAllProducts()
    {
        const response = await axios.get(this.baseUrl);
        return response.data;
    }
}

const productService = new ProductService();
export default productService;