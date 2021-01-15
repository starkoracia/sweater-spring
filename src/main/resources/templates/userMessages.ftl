<#import "macros/common.ftl" as c>

<@c.page>
    <div class="container">
        <div class="row ml-3 mb-3">
            <h3 class="mr-3">${userChannel.username}</h3>
            <#if !isCurrentUser>
                <#if isSubscriber>
                    <a class="btn btn-info" href="/user/unsubscribe/${userChannel.id}">Unsubscribe</a>
                <#else>
                    <a class="btn btn-info" href="/user/subscribe/${userChannel.id}">Subscribe</a>
                </#if>
            </#if>
        </div>
    </div>
    <div class="container mb-3">
        <div class="row">
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscriptions</div>
                        <h3 class="card-text">
                            <a href="/user/subscriptions/${userChannel.id}/list">${subscriptionsCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Subscribers</div>
                        <h3 class="card-text">
                            <a href="/user/subscribers/${userChannel.id}/list">${subscribersCount}</a>
                        </h3>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <#if isCurrentUser>
        <#include "messageEditor.ftl" />
    </#if>
    <#include "messageList.ftl" />
</@c.page>