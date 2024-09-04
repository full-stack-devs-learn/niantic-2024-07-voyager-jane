let categoryId;

document.addEventListener("DOMContentLoaded", () => {
    const dropDown = document.getElementById("category");
    dropDown.addEventListener("change", (event) =>
    {
        categoryId = event.target.value;
        loadPage();
    })
})

function loadPage()
{
    const container = document.getElementById("products-container");
    container.innerHTML = "";

    const url = `/products/${categoryId}/page`;

    fetch(url).then(response => {
//        if (response.status === 200)
        return response.text()
//        else throw new Error(response);
    })
    .then(data => {
        container.innerHTML = data;
    })
    .catch(error => {
        console.log(error);
    })
}