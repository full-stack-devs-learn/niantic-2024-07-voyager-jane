import categoryService from "../../../services/category-service"

export default function ProductAdd()
{
    // let categories = []
    // categoryService.getAllCategories().then(data => {
    //     data.forEach(category => {
    //         categories.push(category);
    //     })
    // });
    // console.log(categories);
    

    return (
        <div className="container">

            <h2>Add Product</h2>

            <form>
                <div className="row">
                    <label htmlFor="product-name">Product Name:</label>
                    <input type="text" className="form-control" name="product-name" id="product-name"/>
                </div>

                {/* <div className="row">
                    <label htmlFor="category">Choose a Category:</label>
                    <select name="category" id="category">
                        {categories.map((category) => (
                            <option key={category.categoryId} value={category.categoryId}>{category.categoryName}</option>
                        ))}
                    </select>
                </div> */}

                <div className="row">
                    <label htmlFor="quantity-per-unit">Quantity Per Unit:</label>
                    <input type="text" className="form-control" name="quantity-per-unit" id="quantity-per-unit" />
                </div>

                <div className="row">
                    <label htmlFor="unit-price">Unit Price: </label>
                    <input type="text" name="unit-price" id="unit-price" />
                </div>
            </form>
        </div>
    )
}