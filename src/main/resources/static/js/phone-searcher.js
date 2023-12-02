const ActiveFilter = document.getElementById("ActiveFilter");
const Table = document.getElementById("phones-table");

function filterByActive() {
    filter(1, ActiveFilter)
}

$(document).ready(function () {
    createFilter(1, ActiveFilter)
});

function searchByPhone() {
    let phrase = document.getElementById('search-phone');
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

function validateTimeInput(input) {
    const pattern = /^([01]?[0-9]|2[0-3]):[0-5][0-9]$/;
    const value = input.value;

    if (!pattern.test(value)) {
        input.setCustomValidity('Please enter a valid time in HH:mm format (00:00 - 23:59)');
    } else {
        const [hours, minutes] = value.split(':');
        if (parseInt(hours) > 23 || (parseInt(hours) === 23 && parseInt(minutes) > 59)) {
            input.setCustomValidity('Please enter a valid time in HH:mm format (00:00 - 23:59)');
        } else {
            input.setCustomValidity('');
        }
    }
}

const callTimeSumInput = document.getElementById('callTimeSum');

function setInitialTime() {
    callTimeSumInput.value = '00:00';
}

setInitialTime();