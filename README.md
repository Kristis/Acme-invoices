## Synopsis

Acme-invoices is a small project wich was create as programming task for one company. It's very basic REST API wich return basic customer and invoices information.
Technological stack is: Spring-Boot, Spring-Data, Neo4j.ffff
For the first time I used NoSql database in project.

## Code Example

Problem that this project try to solve is get data for invoices generation. This data are getting thrue REST API. Right now API support there functions:

###### Get all customer invoices
```ffffff
http://localhost:8080/sysapi/v1.0/invoices/?customerId=1
```
###### Get customer invoices by address
```
http://localhost:8080/sysapi/v1.0/invoices/?customerId=1&addressId=xsxx
```

## Motivation


## Installation


## API Reference


## Tests

