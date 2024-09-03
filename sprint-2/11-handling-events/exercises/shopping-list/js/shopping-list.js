let service;
let list = []

let allItemsIncomplete = true;


function displayListTitle() {
    const title = document.getElementById("title")
    title.textContent = service.getListName();
}


function displayShoppingList() {
    const parent = document.getElementById("shopping-list")

    list.forEach(item => {
        addListItem(item, parent);
    })
}

function addListItem(item, parent)
{
    const div = document.createElement("div")
    div.classList.add("list-item");
    if(item.isComplete)
    {
        div.classList.add("complete");
    }

    addItemTitle(item, div);
    addQuantity(item, div);

    div.addEventListener("click", () => 
        {
            if (!div.classList.contains("complete")) div.classList.add("complete");
        });
    
    div.addEventListener("dblclick", () => 
        {
            if (div.classList.contains("complete")) div.classList.remove("complete");
        });

    parent.appendChild(div)
}

function addItemTitle(item, parent)
{
    const div = document.createElement("div")
    div.textContent = item.title;

    parent.appendChild(div);
}

function addQuantity(item, parent)
{
    const div = document.createElement("div");
    div.classList.add("quantity-container");

    const span = document.createElement("span");
    span.textContent = "quantity"
    span.classList.add("super");

    const text = document.createTextNode(item.quantity)

    div.appendChild(span)
    div.appendChild(text)

    parent.appendChild(div);
}

// Toggle All - Mark All (In)Completed Button
function markCompleted(event) 
{
    event.preventDefault();

    const listItems = document.querySelectorAll(".list-item");
    const button = document.getElementById("allCompleteButton");

    if (!allItemsIncomplete)
    {
        listItems.forEach(item => item.classList.remove("complete"));
        button.textContent = "Mark All Complete";
        allItemsIncomplete = true;
    }

    else
    {
        listItems.forEach(item => item.classList.add("complete"));
        button.textContent = "Mark All Incomplete";
        allItemsIncomplete = false;
    }
    
}

// Individual Item Complete - Not needed if we're doing double click but keeping for the memories
// function individualComplete(event, item)
// {
//     event.preventDefault();

//     if (!item.classList.contains("complete"))
//     {
//         item.classList.add("complete");
//     }
    
//     else
//     {
//         item.classList.remove("complete");
//     }

// }

// Add Item
function submitItem(event)
{
    event.preventDefault();

    const itemTitle = document.getElementById("itemName");
    const quantity = document.getElementById("quantity");

    const newItem = {
        id: list.length + 1,
        title: itemTitle.value,
        quantity: parseInt(quantity.value.trim()),
        isComplete: false
    }

    list.push(newItem);

    const parent = document.getElementById("shopping-list");
    addListItem(newItem, parent);

    clearForm();
}

function clearForm()
{
    const itemTitle = document.getElementById("itemName").value = "";
    const quantity = document.getElementById("quantity").value = "";
}

// create the page load event here

document.addEventListener("DOMContentLoaded", () => {
    service = new ShoppingService();
    list = service.getShoppingList();

    displayListTitle();
    displayShoppingList();

    // Toggle All
    const completeButton = document.getElementById("allCompleteButton");
    completeButton.addEventListener("click", markCompleted);

    // Add New Items
    const form = document.querySelector("form");
    form.addEventListener("submit", submitItem);
});