## About The Api

Service for accepting payments through Yookassa

## Protocols (JSON)
1) Rest Api endpoint /acquiring/api/v1/pay/single-pay
 * Request
 ```
  {
    "adapter": "YOOKASSA",
    "customerId": "customerId_8ef89111d723",
    "amount": 0.00,
    "currency": "RUB",
    "successRedirectLink": "successRedirectLink_4482a2fdbf62",
    "orderId": "orderId_76ffd43293ea",
    "description": "description_470c650c10f0"
  }
 ```
* Response
```
{
  "status": "SUCCESS",
  "body": {},
  "error": {}
}
```
2) RPC through RabbitMQ
  * Request
```
    {
  "requestId": "4dd70c80-135d-4f84-bbca-6e19efc9a6d2",
  "messageType": "ACQ_SINGLE_PAY",
  "messageTimeStamp": "messageTimeStamp_7c884460c041",
  "data": {}
}
```
* Response
```
{
  "responseId": "383172a5-9bc7-4927-b8d9-f9d1c5f8c769",
  "messageType": "ACQ_SINGLE_PAY",
  "messageTimeStamp": "messageTimeStamp_058fe7bc4b87",
  "data": {}
}
```

### Yookassa connection
1) Register
2) Get shopId and token from settings
3) Paste to properties or in docker-compose.yml 
 ```
acquiring.yookassa.restClient.url=https://api.yookassa.ru/v3
acquiring.yookassa.shopId=urShopId
acquiring.yookassa.token=urToken
```

### RabbitMQ
 ## Change settings in properties or in docker-compose.yml 
```
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=root
spring.rabbitmq.password=root
```

### PostgreSQL
  ## Change settings in properties or in docker-compose.yml 
```
spring.datasource.url=jdbc:postgresql://localhost:5432/pay-service
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver
```

### Docker 
Configurate in docker-compose.yml start as 
```
docker compose up
```

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.4/gradle-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#appendix.configuration-metadata.annotation-processor)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#using.devtools)
* [Validation](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#io.validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.4/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

### License

Distributed under the MIT License.See `LICENSE.txt` for more information.
