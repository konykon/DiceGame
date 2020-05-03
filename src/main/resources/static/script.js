function fetchJson(url) {
    return fetch(url, {
        method: 'GET',
        credentials: 'include'
    }).then(function (response) {
        if (response.ok) {
            return response.json();
        }
        throw new Error(response.statusText);
    });
}

$(document).ready(function () {
    fetchJson('http://localhost:8080/players')
        .then(function (data) {
            fillTable(data);
            console.log(data);
        })
});

function addCell(tr, val) {
    let td = document.createElement('td');
    td.innerHTML = val;
    tr.appendChild(td);
}

function addRow(table, val_1, val_2, val_3, val_4, val_5) {
    let tr = document.createElement('tr');
    addCell(tr, val_1);
    addCell(tr, val_2);
    addCell(tr, val_3);
    addCell(tr, val_4);
    addCell(tr, val_5);
    table.appendChild(tr);
}

function fillTable(data) {
    let table = document.getElementById('tablePlayers');
    data.forEach(player => {
        addRow(table, player.id, player.name, player.date, player.successPct, `<p id=${player.id} onClick=editPlayer(this.id)>edit</p>`);
    });
}

function createPlayer() {
    let url = 'http://localhost:8080/players';
    let name = document.getElementById('player').value;
    const player = {
        name: name
    };
    $.ajax({
        type: 'POST',
        url: url,
        data: JSON.stringify(player),
        headers: { 'Content-Type': 'application/json' },
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(error);
        }
    });
}

function editPlayer(id) {
    let newName = prompt('Insert new name:');
    fetchJson('http://localhost:8080/players')
        .then(function (data) {
            data.forEach(player => {
                if (id == player.id) {
                    $.ajax({
                        type: 'PUT',
                        url: 'http://localhost:8080/players/' + id,
                        data: JSON.stringify({name: newName}),
                        headers: { 'Content-Type': 'application/json' },
                        success: function (data) {
                            alert(data.name);
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });
                }
            });
        });
}


function rollDice() {
    const dice = [...document.querySelectorAll(".die-list")];
    dice.forEach(die => {
      toggleClasses(die);
      console.log(die);
      die.dataset.roll = getRandomNumber(1, 6);
    });
  }
  
  function toggleClasses(die) {
    die.classList.toggle("odd-roll");
    die.classList.toggle("even-roll");
  }
  
  function getRandomNumber(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }
  

// let found = false;
//     let option;
//     if (clientes.length == 0) {
//         clientes.push(cliente);
//         option = document.createElement("option");
//         option.innerHTML = `${nombre} ${apellido}`;
//         option.id = apellido + 1;
//         document.getElementById("listaClientes").appendChild(option);
//     } else {
//         clientes.forEach(cliente => {
//             if (nombre == cliente.nombre && apellido == cliente.apellido) {
//                 found = true;
//                 alert(`Ya existe un cliente con los datos introducidos`);
//             }
//         });
//         if (found == false) {
//             clientes.push(cliente);
//             option = document.createElement("option");
//             option.innerHTML = `${nombre} ${apellido}`;
//             option.id = apellido + clientes.length;
//             found = true;
//             document.getElementById("listaClientes").appendChild(option);
//         }
//     }   

// function createPicture() {
//     let nameOfShop = document.getElementById('nameOfShop').value;
//     let name = document.getElementById('picture').value;
//     let author = document.getElementById('author').value;
//     fetchJson('http://localhost:8080/shops')
//         .then(function (data) {
//             data.forEach(shop => {
//                 if (nameOfShop == shop.name) {
//                     let id = shop.id;
//                     const picture = {
//                         name: name,
//                         author: author,
//                         shop_id: id
//                     };
//                     $.ajax({
//                         type: 'POST',
//                         url: 'http://localhost:8080/shops/' + id + '/pictures',
//                         data: JSON.stringify(picture),
//                         headers: { 'Content-Type': 'application/json' },
//                         success: function (data) {
//                             shop.pictures = data;
//                             console.log(data);
//                         },
//                         error: function (error) {
//                             console.log(error);
//                         }
//                     });
//                 }
//             });
//         });
// }

// function showPictures() {
//     let nameOfShop = document.getElementById('nameShop').value;
//     let table = document.getElementById('tablePictures');
//     table.innerHTML = "";
//     fetchJson('http://localhost:8080/shops')
//         .then(function (data) {
//             data.forEach(shop => {
//                 if (nameOfShop == shop.name) {
//                     let id = shop.id;
//                     fetchJson('http://localhost:8080/shops/' + id + '/pictures')
//                         .then(function (data) {
//                             data.forEach(picture => {
//                                 addRow(table, picture.id, picture.name, picture.author);
//                             });
//                         });
//                 }
//             });
//         });

// }

// function deletePicture() {
//     let nameOfShop = document.getElementById('shopName').value;
//     let pictureName = document.getElementById('pictureToDelete').value;
//     fetchJson('http://localhost:8080/shops')
//         .then(function (data) {
//             data.forEach(shop => {
//                 if (nameOfShop == shop.name) {
//                     let shop_id = shop.id;
//                     fetchJson('http://localhost:8080/shops/' + shop_id + '/pictures')
//                         .then(function (data) {
//                             data.forEach(picture => {
//                                 if (pictureName == picture.name) {
//                                     let id = picture.id;
//                                     $.ajax({
//                                         type: 'DELETE',
//                                         url: 'http://localhost:8080/shops/' + shop_id + '/pictures/' + id,
//                                         headers: { 'Content-Type': 'application/json' },
//                                         success: function () {
//                                             document.getElementById("msgDel").innerHTML = `El cuadro esta eliminado.`;
//                                         },
//                                         error: function (error) {
//                                             console.log(error);
//                                         }
//                                     });
//                                 }
//                             });
//                         });
//                 }
//             });
//         });
// }

