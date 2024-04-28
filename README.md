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

### License

Distributed under the MIT License.See `LICENSE.txt` for more information.
