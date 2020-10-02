# SEPT Startup code and  project Structure documentation 

# Quick Start
### port set
.env
```
PORT = 8080
```

### project structure
```bash
├── BackEnd
│   ├── src
│   │   ├── main
|   |   |   ├── java
|   |   |   |   ├── OnlineBookingSystem
|   |   |   |   |   ├── Controllers
|   |   |   |   |   |   ├── AvailabilityController.java
|   |   |   |   |   |   ├── BookingController.java
|   |   |   |   |   |   ├── BusinessOwnerController.java
|   |   |   |   |   |   ├── CustomerController.java
|   |   |   |   |   |   ├── EmployeeController.java
|   |   |   |   |   |   ├── LoginController.java
|   |   |   |   |   |   ├── RegisterController.java
|   |   |   |   |   ├── DisplayClasses
|   |   |   |   |   |   ├── BookingLink.java
|   |   |   |   |   |   ├── CheckCell.java
|   |   |   |   |   |   ├── Link.java
|   |   |   |   |   |   ├── LinkCell.java
|   |   |   |   |   |   ├── Option.java
|   |   |   |   |   |   ├── ShiftCell.java
|   |   |   |   |   |   ├── ShiftStatus.java
|   |   |   |   |   |   ├── TableCell.java
|   |   |   |   |   |   ├── TableRow.java
|   |   |   |   |   ├── ModelClasses
|   |   |   |   |   |   ├── Booking.java
|   |   |   |   |   |   ├── BusinessOwner.java
|   |   |   |   |   |   ├── BusinessService.java
|   |   |   |   |   |   ├── Customer.java
|   |   |   |   |   |   ├── Database.java
|   |   |   |   |   |   ├── Day.java
|   |   |   |   |   |   ├── Employee.java
|   |   |   |   |   |   ├── Interface.java
|   |   |   |   |   |   ├── Model.java
|   |   |   |   |   |   ├── Role.java
|   |   |   |   |   |   ├── Shift.java
|   |   |   |   |   |   ├── Specialisation.java
|   |   |   |   |   |   ├── User.java
|   |   |   |   |   |   ├── Work.java
|   |   |   |   |   |   ├── WorkShift.java
|   |   |   |   |   ├── OnlineBooking.java
|   |   |   |   |   ├── SystemException.java
|   |   |   ├── resouces
|   |   |   |   ├── SQL
|   |   |   |   |   ├── createdb.sql
|   |   |   |   |   ├── createShift.sql
|   |   |   |   |   ├── initLiveDb.sql
|   |   |   |   |   ├── initTestDb.sql
|   |   |   |   ├── application.properties
|   |   ├── test
|   |   |   ├── java
|   |   |   |   ├── OnlineBookingSystem
|   |   |   |   |   ├── BookingControllerTest.java
|   |   |   |   |   ├── BusinessOwnerControllerTest.java
|   |   |   |   |   ├── BusinessServiceTest.java
|   |   |   |   |   ├── EmployeeControllerTest.java
|   |   |   |   |   ├── LoginControllerTest.java
│   ├── .gitignore
│   ├── onlineBooking.db
│   ├── pom.xml
├── Frontend
│   ├── public
│   │   ├── favicon.ico
│   │   ├── index.html
│   │   ├── logo192.png
│   │   ├── logo512.png
│   │   ├── manifest.json
│   │   ├── robots.txt
│   ├── src
│   │   ├── components
|   |   |   ├── board-admin.component.js
|   |   |   ├── board-user.component.js
|   |   |   ├── board-worker.component.js
|   |   |   ├── business-register.component.js
|   |   |   ├── home.component.js
|   |   |   ├── login.component.js
|   |   |   ├── profile.component.js
|   |   |   ├── user-booking-history.component.js
|   |   |   ├── user-edit-details.component.js
|   |   |   ├── user-register.component.js
|   |   |   ├── user-SearchBooking.component.js
|   |   |   ├── user-ViewAvailble.component.js
│   │   ├── services
|   |   |   ├── auth.service.js
|   |   |   ├── auth-header.js
|   |   |   ├── user.service.js
│   │   ├── App.css
│   │   ├── App.js
│   │   ├── App.test.js
│   │   ├── index.css
│   │   ├── index.js
│   │   ├── logo.svg
│   │   ├── serviceWorker.js
│   │   ├── setupTests.js
│   ├── .env
│   ├── .gitgnore
│   ├── index.html
│   ├── index-BookAppointment.js
│   ├── index-BookCancellation.js
│   ├── package.json
│   ├── README.md
│   ├── style.css
├── UnitTests
│   ├── .gitgnore
│   |   ├── BookingControllerTest.java
|   |   ├── BusinessOwnerControllerTest.java
|   |   ├── BusinessServiceTest.java
|   |   ├── EmployeeControllerTest.java
|   |   ├── LoginControllerTest.java
|   |   ├── SeptBookingSystemBackendApplication.java
|   |   ├── README.md
|   |   ├── CusterTest.java
├── docs
│   ├── Meeting Minutes.pdf
│   ├── Peer of Contribution.pdf
│   ├── README.md
│   ├── Sprint Plan.pdf
│   ├── Sprint Retro.pdf
├── onlineBooking.db
├── package-lock.json
├── README.md
└── .gitignore
```