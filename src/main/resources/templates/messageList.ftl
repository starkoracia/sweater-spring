<#include "security.ftl">

<div class="mt-4">
    <div class="card-columns">
        <#list messages as message>
            <div class="card m-2" style="width: 16rem;">
                <#if message.filename??>
                    <div class="img-bg">
                        <img src="/img/${message.filename}" class="card-img-top"
                             style="max-width: max-content; max-height: max-content;"/>
                    </div>
                </#if>
                <div class="card-body">
                    <div class="card-header text-right">
                        <small class="text-muted">${message.tag}</small>
                    </div>
                    <p class="card-text">${message.text}</p>
                    <div class="blockquote-footer">
                        <a href="/user-messages/${message.author.id}">${message.authorName}</a><br/>
                        <#if message.author.id == currentUserId>
                            <a class="btn btn-primary mt-2" href="/user-messages/${message.author.id}?message=${message.id}">
                                Edit
                            </a>
                        </#if>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</div>