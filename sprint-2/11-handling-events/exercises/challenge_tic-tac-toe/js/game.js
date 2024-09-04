// players player 1,2
const player1 = {
    name: 'Player One',
    value: 'X'
}
const player2 = {
    name: 'Player Two',
    value: 'O'
}

let squares;

// current
let currentPlayer;


function setNextPlayer()
{
    if (currentPlayer.value === 'X') currentPlayer = player2;
    else currentPlayer = player1;
}


function displayPlayer()
{
    const name = document.getElementById("playerName");
    name.textContent = currentPlayer.name;
}


function init()
{
    currentPlayer = player1;
    displayPlayer();
}


function markSquare(event, square)
{
    event.preventDefault();

    const playerSymbol = currentPlayer.value;
    
    if (square.textContent === "")
    {
        square.textContent = playerSymbol;
        setNextPlayer();
        displayPlayer();
    }
}

function resetBoard(event)
{
    event.preventDefault();

    squares.forEach(square => square.textContent = "");
    init();
}


// main
document.addEventListener('DOMContentLoaded', () =>
{
    init()

    squares = document.querySelectorAll(".btn.btn-secondary.game-button");

    squares.forEach(square => {
        square.addEventListener("click", (event) => {
            markSquare(event, square);
        })
    });

    const resetButton = document.getElementById("resetButton");
    resetButton.addEventListener("click", resetBoard);
})
