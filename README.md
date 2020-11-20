# e-bank_exchange-rate

Exchange rate microservice is an additional microservice that may be used by [e-bank](https://github.com/lstefaniszyn/e-bank_) application. It is a mocked service that offers single endpoint for exchanging currencies.

## Running exchange rate service

```bash
mvn spring-boot:run -DskipTests=true -Dspring.mainClass=com.example.exchangerate.microservice.ExchangeRateApplication
```
