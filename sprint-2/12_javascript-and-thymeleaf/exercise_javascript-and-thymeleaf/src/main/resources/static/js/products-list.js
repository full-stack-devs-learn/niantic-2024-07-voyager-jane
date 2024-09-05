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

    const url = `/products/${categoryId}/display`;

    fetch(url).then(response => {
        return response.text()
    })
    .then(data => {
        container.innerHTML = data;
    })
    .catch(error => {
        console.log(error);
    })
}