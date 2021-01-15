<#import "macros/common.ftl" as c>

<@c.page>
    <table class="table table-dark">
        <thead class="thead-light table-bordered">
        <tr>
            <th>Name</th>
            <th>Role</th>
            <th>edit</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a class="edit-user" href="/user/${user.id}">Edit user</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>