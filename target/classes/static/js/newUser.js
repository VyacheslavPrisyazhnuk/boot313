function newUser() {
    let form = window.NewUser.Roles;
    let new_Roles = []
    let rolesList = document.createElement('ul');

    for (let i = 0; i < form.length; i++) {
        let option = form.options[i];
        if (option.selected) {
            new_Roles.push(option.value);
        }
    }

    console.log(new_Roles)

    fetch('http://localhost:8080/create?roler=' + new_Roles, {
        method: 'POST',
        body: JSON.stringify(
            {
            name: window.NewUser.Name.value,
            age: window.NewUser.Age.value,
            email: window.NewUser.Email.value,
            password: window.NewUser.Password.value,

        }
        ),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })

        .then(response => response.json())
        .then(user => {
            $('#tBody tr:last').after('<tr id=' + user.id + '>' +
                '<td>' + user.id + '</td>' +
                '<td>' + window.NewUser.Name.value + '</td>' +
                '<td>' + window.NewUser.Age.value + '</td>' +
                '<td>' + window.NewUser.Email.value + '</td>' +
                '<td>' + window.NewUser.Roles.value + '</td>' +
                '<td> <button type="button" onclick="getModalEdit(' + user.id + ')" class="btn btn-primary btn-sm">Edit</button> </td>' +
                '<td> <button type="button" onclick="getModalDelete(' + user.id + ')" class="btn btn-danger btn-sm">Delete</button> </td>' +
                '</tr>');

            window.NewUser.Name.value = "";
            window.NewUser.Age.value = "";
            window.NewUser.Email.value = "";
            window.NewUser.Password.value = "";
            window.NewUser.Roles.value = "";

            $('#NewUserCreated').modal();
        });
}