# Vehicle Registration System
The Vehicle Registration System is a comprehensive full-stack web application developed using Spring Boot and React. It serves as the final project of a 6-month full-stack bootcamp run by Patika and Kodluyoruz, and is designed to showcase the skills and knowledge acquired during the programme. This project includes various functionalities such as listing, registering, deleting and editing vehicles, as well as user authentication through login and registration processes.

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
- Swagger
- React.js
- Tailwind
- Formik
- Axios

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

### Json
```
[
  {
    "id": 0,
    "accountCreationDate": "string",
    "userName": "string",
    "mail": "string",
    "firstName": "string",
    "lastName": "string",
    "city": "ADANA",
    "vehicles": [
      {
        "id": 0,
        "name": "string",
        "brand": "string",
        "model": "string",
        "plateCode": "string",
        "vehiclesCreationDate": "string",
        "modelYear": 0,
        "active": true
      }
    ]
  }
]
```
