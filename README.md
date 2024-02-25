# User crud operation

a rest api for user crud operations in quarkus and postgres

## Instructions

1. Create a database "blog_app"

```bash
psql -U postgres
```

```sql
create database blog_app;
```

2. run the application using

```bash
mvn quarkus:dev
```

### Rest api

| Function                             | Route                  | Type   | Return                                 |
| ------------------------------------ | ---------------------- | ------ | -------------------------------------- |
| Create a user                        | /api/users/register    | POST   | user                                   |
| Retrive a user by email and password | /api/users/login/{id}  | GET    | user                                   |
| Update a user by id                  | /api/users/update/{id} | UPDATE | Message denoting user update operation |
| Delete a user by id                  | /api/user/delete/{id}  | DELETE | Deleted user                           |

### BDD

Feature: User Registration
A user wants to be able to register with my name, email, and password.

Scenario: Register user with valid information
Given: the user provides their name, email, and password.
When: the registration request is sent.
Then: a new user account is created with the provided information
and the user receives a created user info with an id field.

Scenario: Register user with insufficient information
Given: the user provides insufficient information.
When: the registration request is sent.
Then: The user will not be created, and a message indicating the missing data will be sent
Scenario: Register user with existing email
Given: the user provides their information.
When: the registration request is sent.
Then: The user will not be created, and a message "Email already taken" is sent.

Feature: Getting a user by email and password
As a user,
I want to be able to get my user info using my credential .

Scenario: Get user by correct credentials
Given: the user provide valid credentials
When: the get user by email and password request is sent.
Then: the user will get all the info without a password.

Scenario: Get user by wrong credentials
Given: the user provide wrong credentials
When: the get user by email and password request is sent.
Then: the user will receive an error message "Wrong credentials!" .
Feature: Updating a user by id
As a user,
I want to be able to update my user info using id .

Scenario: Update user by valid id and new information which may contains first name , last name and password
Given: the user provides user id and new information.
When: the update user by id request is sent.
Then: the existing user information will get updated with new user information and the user will receive a message “user has been updated successfully”.

Scenario: Update user by invalid id and new information
Given: the user provided with invalid user id and new information.
When: the update user by id request is sent.
Then: the existing user information will not get updated and the user will receive an error message "User doesn't exist with id: {id}” and updating fails.

Feature: Deleting a user by id
As a user,
I want to be able to delete a user using id .

Scenario: Delete user by valid id
Given: the user provides a valid user id.
When: the delete user by id request is sent.
Then: the user will be deleted and the user will receive the user info without a password field.

Scenario: Delete user by invalid id
Given: the user provides a invalid user id.
When: the delete user by id request is sent.
Then: no user will be deleted and the user will receive a msg “User doesn't exist with id: {id}”
