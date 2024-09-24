class ProductsService {

    baseUrl = `${config.baseUrl}/products`;

    getAllProductsByCatId(catId)
    {
        return axios.get(this.baseUrl + `?catId=${catId}`).then(response => {
            return response.data;
        });
    }

}