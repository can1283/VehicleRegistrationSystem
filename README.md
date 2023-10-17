# Vehicle Registration System


## Tech Stack
- Spring Boot
- Spring Data
- React.js
- MySQL Database
- Hibernate
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

## Project Screen Views
