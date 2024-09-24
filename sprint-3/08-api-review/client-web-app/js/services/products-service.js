class ProductsService {

    baseUrl = `${config.baseUrl}/products`;

    getProductsByCatId(catId)
    {
        return axios.get(this.baseUrl + `?catId=${catId}`)
            .then(response => {
                return response.data;
            });
    }

    addProduct(product)
    {
        return axios.post(this.baseUrl, category)
            .then(response => {
                return response.data;
            })
    }

}