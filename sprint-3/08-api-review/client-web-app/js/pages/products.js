let categoryService;
let productService;
let addFormScreen;
let addForm;
let categoryId;
let catList;

document.addEventListener("DOMContentLoaded", function() {
    categoryService = new CategoryService();
    productService = new ProductsService();

    addFormScreen = document.getElementById("add-form-screen");
    addForm = document.getElementById("add-form");
    catList = document.getElementById("categories-list");

    document.getElementById("add-button").addEventListener("click", showForm);
    document.getElementById("cancel-button").addEventListener("click", cancelAdd);
    document.getElementById("save-button").addEventListener("click", addCategory);

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    categoryId = urlParams.get("catId");

    pickCategory();
})

function pickCategory()
{
    categoryService.getAllCategories().then(categories => {

        categories.forEach(category => {
            const li = document.createElement("li");
            const a = document.createElement("button");
            a.textContent = category.categoryName;

            a.addEventListener("click", () => {
                loadProducts(category.categoryId);
            });
            
            li.appendChild(a);
            catList.appendChild(li);
        })
        
    })
}

function loadProducts(catId)
{
    // load all products
    let productPromise = productService.getAllProductsByCatId(catId);

    productPromise.then(products => {
        const productContainer = document.getElementById('products-container');
        productContainer.innerHTML = '';

        products.forEach(product => {
            const template = document.getElementById('category-template').content.cloneNode(true);
                template.getElementById('category-header').innerText = product.name;

            productContainer.appendChild(template);
        })
    })
}

function showForm()
{
    addFormScreen.classList.remove("d-none");
}

function cancelAdd(event)
{
    event.preventDefault();
    addFormScreen.classList.add("d-none");
}

function addCategory(event)
{
    event.preventDefault();
    event.stopPropagation()

    addForm.classList.add("was-validated");

    if(addForm.checkValidity()){

        addFormScreen.classList.add("d-none");
        addForm.classList.remove("was-validated");
    }
}