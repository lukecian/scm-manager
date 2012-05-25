Name:           ${user.name}
Display Name:   ${user.displayName}
Type:           ${user.type}
E-Mail:         ${user.mail!""}
Active:         ${user.admin?string}
Administrator:  ${user.admin?string}
Creation-Date:  <#if user.creationDate??>${user.creationDate?string("yyyy-MM-dd HH:mm:ss")}</#if>
Last-Modified:  <#if user.lastModified??>${user.lastModified?string("yyyy-MM-dd HH:mm:ss")}</#if>
