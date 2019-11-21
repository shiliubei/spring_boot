$(document).ready(function () {

    usersTable();
    getRoles();

    $("#addUser").on('click', function () {
        var userRoles;
        var rolesArray = [];
        $("#addUserRoles option:selected").each(function () {
            rolesArray.push({id: $(this).attr("roleId")});
        });
        userRoles = JSON.parse(JSON.stringify(rolesArray));
        var addUser = {
            name: $("#addUserName").val(),
            password: $("#addUserPassword").val(),
            roles: userRoles
        };
        $.ajax({
            type: "POST",
            url: "/admin/rest/adduser",
            data: JSON.stringify(addUser),
            contentType: "application/json",
            success: function (data) {
                usersTable();
                $('form[name=addUserForm]').trigger('reset');
                $("#formAdd")[0].reset()
                $("#home").tab('active')
                //name = "addUserForm"
                //window.location.assign("http://localhost:8080/admin/userslist");
            },
            error: function (err) {
                console.log(err);
            }
        });
        $('form[name=addUserForm]').trigger('reset');
        //$('form[name=myForm]').trigger('reset');
    });


    $("#editUserButton").on('click', function () {
        var userRoles;
        var rolesArray = [];
        $("#editUserRoles option:selected").each(function () {
            rolesArray.push({id: $(this).attr("roleId")});
        });
        userRoles = JSON.parse(JSON.stringify(rolesArray));
        var editUser = {
            id: $("#editId").val(),
            name: $("#editName").val(),
            password: $("#editPassword").val(),
            roles: userRoles
        };
        $.ajax({
            type: "POST",
            url: "/admin/rest/edituser",
            data: JSON.stringify(editUser),
            contentType: "application/json",
            success: function (data) {
                usersTable();
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

    $("#deleteUserButton").on('click', function () {

        var id = $('#deleteId').val();
        $.ajax({
            type: "GET",
            url: "/admin/rest/deleteuser/" + id,
            contentType: "application/json",
            success: function (data) {
                usersTable();
            },
            error: function (data) {
                console.log(data)
            }
        });
    })

});

function usersTable() {
    $("#usersListTable").empty();
    $.ajax({
        type: "GET",
        url: "/admin/rest/userslist",
        contentType: "application/json",
        success: function (data) {
            var usersList = JSON.parse(JSON.stringify(data));
            for (var i in usersList) {
                $("#usersListTable").append(
                    "<tr> \
                    <th class=\"font-weight-normal\">" + usersList[i].id + "</th> \
                            <th class=\"font-weight-normal\">" + usersList[i].name + "</th>> \
                            <th class=\"font-weight-normal\">" + usersList[i].password + "</th> \
                            <th class=\\\"font-weight-normal\\\">" + getUserRoles(usersList[i].roles) + "</th> \
                            <th> <button onclick='editModal(" + usersList[i].id + ")' type=\"button\" class=\"btn btn-primary\" \
                            data-toggle=\"modal\" data-target=\"#editUserModal\">Edit</button> </th> \
                            <th> <button onclick='deleteModal(" + usersList[i].id + ")' type=\"button\" class=\"btn btn-danger\" \
                            data-toggle=\"modal\" data-target=\"#deleteUserModal\">Delete</button> </th> \
                    </tr>"
                );
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function deleteModal(id) {
    $.ajax({
        type: "GET",
        url: "/admin/rest/user/" + id,
        contentType: "application/json",
        success: function (data) {
            var user = JSON.parse(JSON.stringify(data));
            $("#deleteId").val(user.id);
        },
        error: function (data) {
            console.log(data)
        }
    });
}

function editModal(id) {
    $.ajax({
        type: "GET",
        url: "/admin/rest/user/" + id,
        contentType: "application/json",
        success: function (data) {
            var user = JSON.parse(JSON.stringify(data));
            $("#editId").val(user.id);
            $("#editName").val(user.name);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function getUserRoles(rolesList) {
    var userRoles = [];
    for (var i in rolesList) {
        userRoles[i] = rolesList[i].role;
    }
    return userRoles;
}

function getRoles() {
    $.ajax({
        type: "GET",
        url: "/admin/rest/roles",
        contentType: "application/json",
        success: function (data) {
            var roles = JSON.parse(JSON.stringify(data));
            for (var i in roles) {
                $("#addUserRoles, #editUserRoles").append(
                    "<option roleId=" + roles[i].id + ">" + roles[i].role + "</option>"
                );
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}


