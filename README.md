# Vehicle Registration System
The vehicle registration system is a comprehensive full-stack web application developed using Spring Boot and React. It serves as the final project of a 6-month full-stack bootcamp run by Patika and Kodluyoruz, and is designed to showcase the skills and knowledge acquired during the programme. This project includes various functionalities such as listing, registering, deleting and editing vehicles, as well as user authentication through login and registration processes.

### Key features:
List vehicles: The system allows users to view a list of registered vehicles, providing essential details and information.
Register vehicles: Users can easily add new vehicles to the system by providing the necessary vehicle details, ensuring a well-organised and up-to-date vehicle register.
Edit vehicles: The system allows users to update and change existing vehicle information, keeping records accurate and up to date.
Delete vehicles: Users have the ability to remove vehicles from the system, providing flexibility and control over the registry.

## Tech Stack
- Spring Boot
- Spring Data
- MySQL Database
- Hibernate
- React.js
- Tailwind

## Requirements
- JDK 20
- Node.js
- MySQL

## Project Structure

```
backend = src/main/java/com/vehicleregistrationsystem/finalcase  
frontend = src/main/js/vrs
```

## Database Configuration
```
spring.datasource.url=jdbc:mysql://localhost:3306/vrs
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Build & Run
### Backend
```
./mvnw spring-boot:run
```
### Frontend
```
cd src/main/js/vrs
npm install
npm start
```

### Port
```
http://localhost:3000
```

### Endpoints
```
GET http://localhost:8080/api/vehicles/byBrand
GET http://localhost:8080/api/vehicles/byModel
GET http://localhost:8080/api/vehicles/user/{{userId}}
GET http://localhost:8080/api/vehicles/user/{{userId}}/slice
GET http://localhost:8080/api/vehicles/user/{{userId}}/vehicles/sorted
POST http://localhost:8080/api/vehicles/{{userId}}
GET http://localhost:8080/api/vehicles/{{vehicleId}}
PUT http://localhost:8080/api/vehicles/{{vehicleId}}
DELETE http://localhost:8080/api/vehicles/{{vehicleId}}
GET http://localhost:8080/api/users
POST http://localhost:8080/api/users
GET http://localhost:8080/api/users/pageable
GET http://localhost:8080/api/users/sorted-by-creation-date
GET http://localhost:8080/api/users/sorted-by-username
GET http://localhost:8080/api/users/{{userId}}
PUT http://localhost:8080/api/users/{{userId}}
DELETE http://localhost:8080/api/users/{{userId}}
POST http://localhost:8080/api/auth/change-password/{{userId}}
POST http://localhost:8080/api/auth/login
POST http://localhost:8080/api/auth/register
```

## Project Screen Views
> https://drive.google.com/drive/folders/1xcGEvHd4wMFBwp3vDeXKmNWtNhxyU9dJ?usp=sharing