Spring Security Demo
=============================

Authentication by token in cookie or from http headers


### FAQ

* cookie domain: server.session.cookie.domain=.XXXX.com

### words

* authentication:  "Authentication" is the process of establishing a principal is who they claim to be (a "principal" generally means a user, device or some other system which can perform an action in your application)
* authorization: refers to the process of deciding whether a principal is allowed to perform an action within your application.
* authority: "permission" or a "right", 批准,准许 grant access to something.
* role: are just "permissions" with a naming convention
* Bcrypt: Blowfish password hashing code  BCryptPasswordEncoder
* token的设计: base64(username + ":" + expirationTime + ":" + md5Hex(username + ":" + expirationTime + ":" password + ":" + key))

### reference

* The Security Filter Chain: https://docs.spring.io/spring-security/site/docs/3.0.x/reference/security-filter-chain.html
