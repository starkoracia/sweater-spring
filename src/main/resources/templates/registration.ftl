
<#-- @ftlvariable name="usernameExistMessage" type="java.lang.String" -->
<#import "macros/common.ftl" as c>
<#import "macros/registr-login-out.ftl" as l>

<@c.page>
    <#if usernameExistMessage?? && usernameExistMessage != "">
        <strong>${usernameExistMessage}</strong><br/>
    </#if>
    <h4>Add new user</h4><br/>
    <form action="/registration" method="post">
        <div class="form-group">
            <div>
                <label> User Name
                    <input type="text" class="form-control ${(usernameError??)?string("is-invalid", "")}"
                           name="username" placeholder="Username" value="<#if user??>${user.username}</#if>"/>
                    <div id="validationTextFeedback" class="invalid-feedback">
                        ${usernameError!}
                    </div>
                </label>
            </div>
            <div>
                <label> Password
                    <input type="password" name="password" value="<#if user??>${user.password}</#if>"
                           class="form-control ${(passwordError??)?string("is-invalid", "")}" placeholder="Password"/>
                    <div id="validationTextFeedback" class="invalid-feedback">
                        ${passwordError!}
                    </div>
                </label>
            </div>
            <div>
                <label> Confirm Password
                    <input type="password" name="password2" value="<#if user??>${user.password2}</#if>"
                           class="form-control ${(passwordMError??)?string("is-invalid", "")}"
                           placeholder="Confirm Password"/>
                    <div id="validationTextFeedback" class="invalid-feedback">
                        ${passwordMError!}
                    </div>
                </label>
            </div>
            <div>
                <label> Email
                    <input type="email" name="email" value="<#if user??>${user.email}</#if>"
                           class="form-control ${(emailError??)?string("is-invalid", "")}"
                           placeholder="some@some.com"/>
                    <div id="validationTextFeedback" class="invalid-feedback">
                        ${emailError!}
                    </div>
                </label>
            </div>
            <@c._csrf_token/>
        </div>
        <div class="g-recaptcha" data-sitekey="6LddcdkZAAAAAMwGnMnTuKMmgZa5xjQqETSRRW0g"></div>
        <#if captchaError??>
            <div class="alert alert-primary" role="alert">
                <strong>${captchaError}</strong>
            </div>
        </#if>
        <div class="mt-3">
            <button type="submit" class="btn btn-primary mr-3">Create User</button>
            <strong>or</strong>
            <a href="/login" class="ml-3">Login</a>
        </div>
    </form>
</@c.page>