# Spring Boot Async Study

## Getting Started
### Prerequisites
* Gradle
* Java 17
* Kotlin
* Docker

```
# Docker

$ docker compose up
```

## Usage
```
# get user data sync

curl --request GET \
  --url http://localhost:8080/users/Wellington/sync \
  --header 'User-Agent: insomnia/9.2.0'
 
# get user data async

curl --request GET \
  --url http://localhost:8080/users/Wellington/async \
  --header 'User-Agent: insomnia/9.2.0'

# get user data async exception

curl --request GET \
  --url http://localhost:8080/users/Wellington/async/exception \
  --header 'User-Agent: insomnia/9.2.0'
 
# get names

curl --request GET \
  --url http://localhost:8085/names \
  --header 'User-Agent: insomnia/9.2.0'
 
# get addresses

curl --request GET \
  --url http://localhost:8085/addresses \
  --header 'User-Agent: insomnia/9.2.0'
  
# get phones

curl --request GET \
  --url http://localhost:8085/phones \
  --header 'User-Agent: insomnia/9.2.0'
```