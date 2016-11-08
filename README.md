## Synopsis

Acme-invoices is a small project wich was create as programming task for one company. It's very basic REST API wich return basic customer and invoices information.
Technological stack is: Spring-Boot, Spring-Data, Neo4j.
For the first time I used NoSql database in project.

## Data structure

Data structure is very simple. Every customer can have one address, many invoices.
One invoice is related with one customer.
```
 /-------------\         N         /-------------\ 
 |   Customer  | ----------------> |   Invoice   |
 \-------------/                   \-------------/
        |
        |
        | 1
        |
        v
  /-------------\   
  |   Address   |
  \-------------/
 ```
  
  More colorful and more represedentive look can be provided with Neo4j tool: <br/>
  ![alt tag](http://i.imgur.com/yVqeLTq.png)
   - Green color: Customer
   - Red color: Address
   - Purple color: Invoice
   - Pink/Yellow color: Invoice start date

## Code Example

Problem that this project try to solve is get data for invoices generation. This data are getting thrue REST API. Right now API support there functions:

###### Get all customer invoices
```ffffff
GET http://localhost:8080/sysapi/v1.0/invoices/?customerId=1
```
###### Get customer invoices by address
```
GET http://localhost:8080/sysapi/v1.0/invoices/?customerId=1&addressId=xsxx
```
###### Get customer invoices by month
```
GET http://localhost:8080/sysapi/v1.0/invoices/?customerId=1&month=11
```
###### Get customer invoices by month and invoice type
```
GET http://localhost:8080/sysapi/v1.0/invoices/?customerId=1filter=shop&month=11
```
###### Generate invoices for customers (Random data). 
```
POST http://localhost:8080/sysapi/v1.0/invoices/
```

###### Responce example
```json
 {
    "invoiceNumber": 8936476887954768310,
    "customer": {
      "customerId": 2,
      "firstName": "Guatemala",
      "middleName": "Roddrigez",
      "lastName": "Fernandp",
      "email": "applePie@email.com",
      "addressId": "12xx11",
      "customerAddress": null,
      "id": 13145
    },
    "invoiceId": "c36b3f22nk6pl556eo7t213nf2",
    "invoicesType": "ADVANCE_PAYMENT",
    "invoiceStatus": "NEW",
    "invoiceTypeLocalized": "test",
    "invoiceDate": "2016-11-07 12:11:42",
    "paymentDueDate": "2016-11-07 12:11:42",
    "startDate": "2016-11-07 12:11:42",
    "endDate": "2016-11-07 12:11:42",
    "periodDescription": " Random invoice",
    "amount": 0.9550119980252494,
    "vat": 0.2,
    "totalAmount": 1.1460143976302992,
    "year": null,
    "month": null,
    "id": 17332
  }
```

## Installation

If you want to run this project on your local machine you need:
- Install Neo4j database on you machine. Download: [Neo4j](https://neo4j.com/download/)
- Create folder with name "import" in  /Users/user/Documents/Neo4j/default.graphdb/
- Create demo data
  - Open folder [Pre-Data](https://github.com/Kristis/Acme-invoices/tree/master/src/db/csv-pre-data)
  - Into new folder "import" put files customer_address.csv, customers.csv customer_invoices.csv
  - Run scripts from [Scripts](https://github.com/Kristis/Acme-invoices/blob/master/src/db/csv-pre-data/scripts-neo4j.txt)
- Build project with '''mvn clean install''' (if you don't have maven use Maven wrapper ```mvnw clean install```)
- Run application:
  - On IDE run AcmeInvoicesDemoApplication
  - On terminal run command ```mvn spring-boot:run```
- Use
  

## Tests
Right now there is only 4 tests.

