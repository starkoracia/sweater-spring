<#import "macros/registr-login-out.ftl" as l>
<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-dark">
    <a class="navbar-brand" href="/">Sweater</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/main">Messages<span class="sr-only">(current)</span></a>
            </li>
            <#if user??>
                <li class="nav-item active">
                    <a class="nav-link" href="/user-messages/${currentUserId}">My messages<span class="sr-only">(current)</span></a>
                </li>
            </#if>
            <#if isAdmin>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/userList">User list<span class="sr-only">(current)</span></a>
                </li>
            </#if>
            <#if user??>
                <li class="nav-item active">
                    <a class="nav-link" href="/user/profile">Profile<span class="sr-only">(current)</span></a>
                </li>
            </#if>
            <li class="nav-item active">

            </li>
        </ul>

        <div class="form-inline mr-3"> <@l.logout/> </div>
        <div class="navbar-text mr-3">${name}</div>
    </div>
</nav>