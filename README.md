# Spring Security Demo Project

## What:
It's a project to demonstrate how to integrate Spring Boot and Spring Security packages with different configurations.

## What to find here:
* Spring security integration and default behavior before configuring (b176f80)
* Configuration with a public and secured endpoints  (f967089) 
* Allow access to the endpoints with different role levels. (bc5a79c)

## To Do:
* Authenticate users with credentials stored in a database
* JWT based authentication

## To Research
* EnableWebMvc annotation
* EnableMethodSecurity
* Check other annotations https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html#annotation-method-interceptors

## What I learned
* Spring Security blocks access to all endpoints without any configuration by default.
* In case it's not configured, Spring Security uses form based authentication.
* `@WebMvcTest` annotation tells Spring Boot to instantiate only web layer not the entire the application context.
* `@SpringBootTest` annotation loads the complete application context.
* MockMvc is not something injectable automatically into tests without configuration. It's necessary to instantiate and configure in the setup hook or using @AutoConfigureMockMvc annotation. Otherwise, autowiring fails with `Could not autowire. No beans of 'MockMvc' type found. ` error. I guess because there is no bean that exists in the test application context by default.
* Learned how to configure MockMvc both with @AutoConfigureMockMvc annotation and manually.
* Role term refers to a specific type of user - like group; Authority term to refer a right to do something - like CAN_READ, CAN_WRITE
* `@WithMockUser` annotation allows mocking a user. The user does not have to exist since it's mocked.