navPanel();
function navPanel() {
    fetch('http://localhost:8080/getAuthorizedUser')
        .then(response => response.json())
        .then(user => {

            document.getElementById("upemail").innerHTML = user.email;

            let rolesList = document.createElement('ul');
            for (let i = 0; i < user.roles.length; i++) {
                let role = document.createElement('li');
                role.textContent = user.roles[i].role.substring(5) + " ";
                rolesList.appendChild(role);
            }
            document.getElementById("uproles").innerHTML = 'with roles: ' + rolesList.textContent;
        });
}