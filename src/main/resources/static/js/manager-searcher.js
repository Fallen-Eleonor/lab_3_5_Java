const StatusFilter = document.getElementById("StatusFilter");
const VIPFilter = document.getElementById("VIPFilter");
const Table = document.getElementById("managers-table");

function filterByStatusFilter() {
    filter(4, StatusFilter)
}

function filterByVIP() {
    filter(5, VIPFilter)
}


$(document).ready(function () {
    createFilter(4, StatusFilter)
    createFilter(5, VIPFilter)
});

function sortByCode() {
    let rows, switching = true, i, x, y, shouldSwitch, col = 2;

    while (switching) {
        switching = false;
        rows = Table.rows;

        for (i = 1; i < (rows.length - 1); i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[col];
            y = rows[i + 1].getElementsByTagName("TD")[col];
            if (parseInt(x.innerHTML) > parseInt(y.innerHTML)) {
                shouldSwitch = true;
                break;
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
}

function searchByFirstName() {
    let phrase = document.getElementById('search-first-name');
    let regPhrase = new RegExp(phrase.value, 'i');
    let flag = false;

    for (let i = 1; i < Table.rows.length; i++) {
        flag = regPhrase.test(Table.rows[i].cells[0].innerHTML);
        if (flag) Table.rows[i].style.display = "";
        else Table.rows[i].style.display = "none";
    }
}

function createFilter(cell, element) {
    let uniqueValues = new Set();

    for (let i = 1; i < Table.rows.length; i++) {
        let value = Table.rows[i].cells[cell].innerHTML

        if (!uniqueValues.has(value)) {
            let option = document.createElement("option");
            option.value = value;
            option.textContent = value;
            element.appendChild(option);
            uniqueValues.add(value);
        }
    }
}

function filter(cell, element) {
    if (element.value !== "all") {
        for (let i = 1; i < Table.rows.length; i++) {
            if (Table.rows[i].cells[cell].innerHTML === element.value) {
                Table.rows[i].style.display = "";
            } else {
                Table.rows[i].style.display = "none";
            }
        }
    } else {
        for (let i = 1; i < Table.rows.length; i++) {
            Table.rows[i].style.display = "";
        }
    }
}