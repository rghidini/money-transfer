# money-transfer
Java RESTFul API to transfer money between accounts - Revolut Backend Test

## Frameworks Used
1. JAX-RS
2. Log4j2
3. H2 in memory DB
4. JUnit for testing

## Services
The API consists in 3 services. One to manage clients (CRUD), one to manage accounts (CRUD) and one to List/Make transfers. It runs on port 8080 and the available services are the following.

### Client Services

#### List all clients
```
URL: /client/
METHOD: GET
```
#### Find client by id
```
URL: /client/{id}
METHOD: GET
```
#### Create a client
```
URL: /client/add
METHOD: POST
CONTENT-TYPE: application/json
BODY: 
{
  "clientName": "Example"
}
```
#### Update a client
```
URL: /client/update/{id}
METHOD: PUT
CONTENT-TYPE: application/json
BODY: 
{
  "clientName":"Example", 
}
```
#### Delete client by id
```
URL: /client/delete/{id}
METHOD: DELETE
```

### Account Services

#### List all accounts
```
URL: /account/
METHOD: GET
```
#### Find account by id
```
URL: /account/{id}
METHOD: GET
```
#### Create an account
```
URL: /account/add
METHOD: POST
CONTENT-TYPE: application/json
BODY: 
{
  "clientId":1, 
  "amount":100
}
```
#### Delete an account by id
```
URL: /account/delete/{id}
METHOD: DELETE
```

### Transfer Services

#### List all transfers
```
URL: /transaction/getAll
METHOD: GET
```

#### Make a transfer between accounts
```
URL: /transaction/do
METHOD: POST
CONTENT-TYPE: application/json
BODY: 
{
  "fromAccountId":2, 
  "toAccountId":1, 
  "amount":90
}
```

## How to use/run

Although the API is provided with JUnit tests, the best way to take a look at the API is to run it with some of the following command and then use examples shown above.

The simplest way is using maven exec command. Download the project and then in its folder run the following command.

* mvn clean compile exec:java
