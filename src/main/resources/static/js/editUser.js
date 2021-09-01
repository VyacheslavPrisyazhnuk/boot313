function editUser() {

    let form = window.editUsers.editRoles;
    let new_Roles = []

    let rolesList = document.createElement('ul');

    for (let i = 0; i < form.length; i++) {
        let option = form.options[i];
        if (option.selected) {
            new_Roles.push(option.value)
        }
    }

    console.log(new_Roles);

    let id = window.editUsers.editID.value;

    fetch('http://localhost:8080/update/rolesEdit=' + new_Roles, {
        method: 'PUT',
        body: JSON.stringify({
            id: window.editUsers.editID.value,
            name: window.editUsers.editName.value,
            age: window.editUsers.editAge.value,
            email: window.editUsers.editEmail.value,
            password: window.editUsers.editPassword.value,
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => {
            $('#' + id).replaceWith('<tr id=' + id + '>' +
                '<td>' + id + '</td>' +
                '<td>' + window.editUsers.editName.value + '</td>' +
                '<td>' + window.editUsers.editAge.value + '</td>' +
                '<td>' + window.editUsers.editEmail.value + '</td>' +
                '<td>' + window.editUsers.editRoles.value + '</td>' +
                '<td> <button type="button" onclick="getModalEdit(' + id + ')" class="btn btn-primary btn-sm">Edit</button> </td>' +
                '<td> <button type="button" onclick="getModalDelete(' + id + ')" class="btn btn-danger btn-sm">Delete</button> </td>' +
                '</tr>');
        });
}