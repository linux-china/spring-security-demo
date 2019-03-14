Spring Security Demo
=============================

Spring Security Demo with following cases:

* Spring Boot Integration
* Reactive support

### Vocabulary

* Principal: Any user, device, or system (application) that would like to interact with your application.
* Authentication: A process by which your application makes sure that the principal is who they claim to be.
* Credentials: When a principal tries to interact with your application, the authentication process kicks in and challenges the principal to pass on some values. One such example is a username/password combination and these values are called credentials. The authentication process validates the principal's passed-in credentials against a data store and replies back with the appropriate result.
* Authorization: After successful authentication, the principal is checked again for actions that it can perform on your application. This process of checking rights for a principal and then granting necessary permissions is called authorization.
* Secured item/resource: The item or resource that is marked as secured and requires the principal (user) to successfully complete both authentication and authorization.
* GrantedAuthority: A Spring Security object (org.springframework.security.core.GrantedAuthority interface) that contains/holds permissions/access-right details of a principal.
* SecurityContext: A Spring Security object that holds a principal's authentication details.
* Authentication by token in cookie or from http headers

Account = principal = UserDetails 

### Vocabulary 2

* authentication:  "Authentication" is the process of establishing a principal is who they claim to be (a "principal" generally means a user, device or some other system which can perform an action in your application)
* authorization: refers to the process of deciding whether a principal is allowed to perform an action within your application.
* authority: "permission" or a "right", 批准,准许 grant access to something.
* role: are just "permissions" with a naming convention
* Bcrypt: Blowfish password hashing code  BCryptPasswordEncoder
* token的设计: base64(username + ":" + expirationTime + ":" + md5Hex(username + ":" + expirationTime + ":" password + ":" + key))

### FAQ

* cookie domain: server.session.cookie.domain=.XXXX.com

### reference

* Spring Security Reference: https://docs.spring.io/spring-security/site/docs/5.1.4.RELEASE/reference/htmlsingle/
