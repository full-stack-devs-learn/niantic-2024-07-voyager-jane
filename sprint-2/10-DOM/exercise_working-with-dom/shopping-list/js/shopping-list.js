// add pageTitle
// add groceries
const service = new ShoppingService();
let pageTitle = service.getListName();
let groceries = service.getShoppingList();


/**
 * This function will get a reference to the title and set its text to the value
 * of the pageTitle variable that was set above.
 */
function displayListTitle() 
{
    const listTitle = document.getElementById("title");

    listTitle.textContent = pageTitle;
}

/**
 * This function will loop over the array of groceries that was set above and add them to the DOM.
 */
function displayGroceries() 
{
    const groceryContainer = document.getElementById("shopping-list");

    groceries.forEach(item => 
        {
            createItemDiv(item, groceryContainer)
        });


}

function createItemDiv(item, parent)
{
    const itemDiv = document.createElement("li");
    itemDiv.classList.add("list-item");

    if (item.isComplete === true)
    {
        itemDiv.classList.add("complete");
    }

    parent.appendChild(itemDiv);

    buildItemTitle(item, itemDiv);
    buildQuantityDiv(item, itemDiv);
}

function buildItemTitle(item, parent)
{
    const titleDiv = document.createElement("div");
    titleDiv.textContent = item.title;

    parent.appendChild(titleDiv);
}

function buildQuantityDiv(item, parent)
{
    const quantityDiv = document.createElement("div");
    quantityDiv.classList.add("quantity-container");

    parent.appendChild(quantityDiv);

    buildQuantityText(item, quantityDiv);
}

function buildQuantityText(item, parent)
{
    const quantityTitle = document.createElement("p");
    quantityTitle.classList.add("super");
    quantityTitle.textContent = "quantity";

    const quantityValue = document.createElement("p");
    quantityValue.textContent = item.quantity;

    parent.appendChild(quantityTitle);
    parent.appendChild(quantityValue);
}

/**
 * This function will be called when the button is clicked. You will need to get a reference
 * to every list item and add the class completed to each one
 */
function markCompleted() 
{
    const listItems = document.querySelectorAll(".list-item");

    listItems.forEach(itemDiv => 
        {
            itemDiv.classList.add("complete");
        });


    // THIS WAS MY ORIGINAL CODE WHERE I THOUGHT WE JUST HAD TO GREY OUT ONLY the objects with isComplete === true but turns out i just had to mark all of them as true no matter what isComplete was set to... keeping it for the memories

    // const listItems = document.querySelectorAll(".list-item");

    // listItems.forEach(itemDiv => 
    //     {
    //         const divTitle = itemDiv.firstChild.textContent;

    //         groceries.forEach(item => 
    //             {
    //                 if (item.title === divTitle && item.isComplete === true)
    //                 {
    //                     itemDiv.classList.add("complete");
    //                 }
    //             }); 
    //     });
}

document.addEventListener("DOMContentLoaded", () => 
{
    displayListTitle();
    displayGroceries();
});

