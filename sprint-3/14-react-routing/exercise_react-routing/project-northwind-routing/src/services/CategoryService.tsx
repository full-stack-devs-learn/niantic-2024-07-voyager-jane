import axios from "axios";
import { Category } from "../models/Category";

class CategoryService {
    baseUrl = "http://localhost:8080/categories"

    async getAllCategories(): Promise<Category[]>
    {
        const response = await axios.get<Category[]>(this.baseUrl)
        return response.data
    }

    async getCategoryById(id: number): Promise<Category>
    {
        const response = await axios.get<Category>(`${this.baseUrl}/${id}`)
        return response.data
    }
}

const categoryService = new CategoryService();
export default categoryService