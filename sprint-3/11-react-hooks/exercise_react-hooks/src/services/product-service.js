import axios from 'axios'

class ProductService
{
    baseUrl = 'http://localhost:8080/products'

    async getAllProducts()
    {
        const response = await axios.get(this.baseUrl);
        return response.data;
    }

    async addProduct(product)
    {
        const response = await axios.post(this.baseUrl, product);
        return response.data;
    }

    async deleteProduct(productId)
    {
        await axios.delete(`${this.baseUrl}/${productId}`)
    }
}

const productService = new ProductService();
export default productService;