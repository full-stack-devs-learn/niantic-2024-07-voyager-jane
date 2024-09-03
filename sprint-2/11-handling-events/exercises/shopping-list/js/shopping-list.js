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
        div.classList.add("complete")
    }

    addItemTitle(item, div);
    addQuantity(item, div)

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

// Mark All (In)Completed Button
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

// Individual Item Complete
function individualComplete(event, item)
{
    event.preventDefault();

    if (!item.classList.contains("complete"))
    {
        item.classList.add("complete");
    }
    
    else
    {
        item.classList.remove("complete");
    }

}

// create the page load event here

document.addEventListener("DOMContentLoaded", () => {
    service = new ShoppingService();
    list = service.getShoppingList();

    displayListTitle();
    displayShoppingList();

    // Individual Complete Click
    // function() is there since calling individualComplete(item) in its place would run the function even if i didn't click so i need to only run function() when the condition is met
    const listItems = document.querySelectorAll(".list-item");
    listItems.forEach(item => {
        item.addEventListener("click", function()
                    {
                        individualComplete(event, item);
                    })
    });

    // Mark Complete Button Click
    const completeButton = document.getElementById("allCompleteButton");
    completeButton.addEventListener("click", markCompleted);

    
});