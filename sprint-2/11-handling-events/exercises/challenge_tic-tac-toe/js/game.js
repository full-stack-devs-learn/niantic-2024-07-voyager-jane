// players player 1,2
const player1 = {
    name: 'Player One',
    value: 'X'
}
const player2 = {
    name: 'Player Two',
    value: 'O'
}

// current
let currentPlayer;


function setNextPlayer()
{
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


function markSquare()
{

}


// main
document.addEventListener('DOMContentLoaded', () =>
{
    init()

    const squares = document.getElementsByClassName(".btn .btn-secondary .game-button");
})
