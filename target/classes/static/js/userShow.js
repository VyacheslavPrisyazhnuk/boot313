showUserInfo();

function showUserInfo(user) {
    fetch('http://localhost:8080/getAuthorizedUser')
        .then(response => response.json())
        .then(user => {
            let tBody = document.getElementById("user_info");
            tBody.innerHTML = "";

            let row = tBody.insertRow(0);
            let cell0 = row.insertCell(0);
            cell0.innerHTML = user.id;
            let cell1 = row.insertCell(1);
            cell1.innerHTML = user.name;
            let cell3 = row.insertCell(2);
            cell3.innerHTML = user.age;
            let cell4 = row.insertCell(3);
            cell4.innerHTML = user.email;
            let cell5 = row.insertCell(4);
            cell5.innerHTML = Roles(user).textContent;
        });
}