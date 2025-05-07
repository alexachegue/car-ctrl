# car-ctrl
CSC340-03 Group 6 Project
## Title
Car Ctrl: Car Repair & Auto Service Provider

## Team Members
Alexa Chegue-Santos, Damaris Hilario-Miranda, Ganiyu Ibrahim

## Description 
An application that connects customers with local mechanics and auto service providers, allowing users to book appointments and leave reviews. Service providers can manage their availability, car services, and respond to customer inquiries. A system administrator ensures quality control by moderating reviews and managing user access.

## Setup Instructions
### First page 
http://localhost:8081/users/

### User login
username: may6user
password: pw

### Provider login
username: ModKing1
password: pass123

## Scenario Summary
Provider: Create provider profile
Provider logs in and creates their profile. Provider adds services they offer to their profile for customers to view and book.

Customer: Create/modify customer profile and view services 
The customer creates their profile and logs in. Customers register their car by entering its details. The customer can view a list of available car services offered by nearby providers.

Customer: Book an appointment and write a review
Customer selects a service and books an appointment with a provider. After the appointment is completed, the customer writes a review for the provider.

Provider: Reply to Review
The provider views reviews left by the customer and may respond to them.

Admin: View Usage statistics, View/delete Reviews, Manage users
The admin views system-wide usage stats and monitors and deletes reviews. Admin manages user accounts for both customers and providers.

## App Functions
1. Customer (Vehicle Owner):  
    i. Create/modify customer profile - Users can register, update personal details, and manage    vehicle information.  
    ii. View available services - Search for mechanics based on location, service type, and customer ratings.  
    iii. Subscribe to available services - Book a date and time to take the vehicle to the mechanic.  
    iv. Write reviews for subscribed services - Provide feedback and rate the quality of service.  

2. Provider (Mechanic/Auto Shop):  
    i. Create/modify/remove provider profile - Provider can create account and register as mechanic.   
    ii. Create services - Display if they provide AC installation/repair, Battery, Brakes, Electrical repair, Engine maintenance, Oil changes, Tire repair, Inspection, and/or Wheel alignment, etc. Show booked/unbooked times for users to book.   
    iii. View customer statistics -  View booked times, their required service, car make/model, and cost.  
    iv. Reply to reviews - Reply to customer reviews.  

3. SysAdmin (Administrator):  
    i. Manage user access - Authorize mechanic registration and terminate fraudulent accounts.  
    ii. Moderate services - Remove service listings that are inaccurate or unsuitable.  
    iii. Moderate reviews - Remove inappropriate or inaccurate reviews.  
    iv. View usage statistics - The administrator is able to view the usage percentage/statistics across the entire spectrum of users.  
