$(document).ready( function () {
    var table = $('#usersTable').DataTable({
        "sAjaxSource": "/userss",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "id"},
            { "mData": "name" },
            { "mData": "lastName" },
            { "mData": "email" },
            { "mData": "fte" },
            { "mData": "status" }
        ]
    })
});