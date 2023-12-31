# Vehicle Registration System
The Vehicle Registration System is a comprehensive full-stack web application developed using Spring Boot and React. It serves as the final project of a 6-month full-stack bootcamp run by [Patika](https://www.patika.dev/tr) and [Kodluyoruz](https://kodluyoruz.org/), and is designed to showcase the skills and knowledge acquired during the programme. This project includes various functionalities such as listing, registering, deleting and editing vehicles, as well as user authentication through login and registration processes.

### Key features:
- List vehicles: The system allows users to view a list of registered vehicles, providing essential details and information.  
- Register vehicles: Users can easily add new vehicles to the system by providing the necessary vehicle details, ensuring a well-organised and up-to-date vehicle register.  
- Edit vehicles: The system allows users to update and change existing vehicle information, keeping records accurate and up to date.  
- Delete vehicles: Users have the ability to remove vehicles from the system, providing flexibility and control over the registry.
- User Login: Allow users to log in to the system using a username and password.
- User Registration: Enable new users to register by filling out a registration form.
- Change Password: Allow users to change their passwords for account security.

## Tech Stack
- Spring Boot
- Hibernate
- Swagger
- MySQL
- React.js
- Tailwind
- Formik
- Axios 
- React Icons

## Requirements
- JDK 20
- Node.js
- MySQL


## App Screen Views
[Drive link of application screenshots](https://drive.google.com/drive/folders/1LdEFmjz_k6AQInVOFLdXQogRcqtEvkwY?usp=sharing)

## Project Structure

```
backend = src/main/java/com/vehicleregistrationsystem/finalcase  
frontend = src/main/js/vrs
```

## App Screen Views
[Drive link of application screenshots](https://drive.google.com/drive/folders/1LdEFmjz_k6AQInVOFLdXQogRcqtEvkwY?usp=sharing)


## Database Configuration
```
spring.datasource.url=jdbc:mysql://localhost:3306/vrs
spring.datasource.username=username
spring.datasource.password=password
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
    "password": "string",
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

User:
{
  "id": 49,
  "account_creation_date": "2023-10-16 14:11:33",
  "city": "ADANA",
  "first_name": "Nurican",
  "last_name": "Kasikci",
  "password": "Password123.",
  "user_name": "nurican",
  "mail": "nuricankasikci@gmail.com"
  "vehicles": []
}
  
Vehicle:
{
  "id": 85,
  "brand": "Porsche",
  "model": "911",
  "model_year": 2022,
  "name": "Porsche 911",
  "plate_code": "07PRS07",
  "user_id": 49,
  "vehicles_creation_date": "2023-10-16 17:01:02",
  "active": true
}
```
