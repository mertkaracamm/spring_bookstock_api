# Online Bookstore Application Rest Api

Project link:

  - ### HerOku : https://spring-bookstore-heroku.herokuapp.com  
  - ### Swagger run on Heroku Server: https://app.swaggerhub.com/apis-docs/mertkaracamm/Online_BookStore_Application_WebApi/1.0.1
  - ### Swagger: https://app.swaggerhub.com/apis-docs/mertkaracamm/Online_BookStore_Application_WebApi/1.0.0
  
  
Used Tehnologies:
  
    - Business Layer : Spring boot web 2.0.5.RELEASE on Java 8/jdk 1.8 version    
    - Database Layer: PostgreSQL
    - Server Layer: HerOku
    - Build Automation: Maven
    - Authentication: Password and token encrypt with AES algorithm with the aid of custom key and init-vector.
    - Logging: Slf4j is used for logging mechanism.
    - Testing: JUnit
    - Exceptions: Custom,Global,Authentication etc.
  
  
 Project Explanation: 
  
   There are 3 controller in the project:
          
Customer Controller: It is used for new customer endpoint. Customer can signup and signin in bookstore. Furthermore All Customer can be listed in this bookstore application. Three rest endpoint in this controller:

    - customer/signup: customers can signup with theirs detail. Token is produced on every signup session.
    - customer/signin: customers can only login on the bookstore with their token which was previously produced in signup.
    - customer/allCustomer: All customer can be listed in tihs booksotre in terms of token because of security issue. (todo: in this section modelling must be based on some roles such as admin etc.)
    
BookStock Controller: It is used for add book and update stock for stock consistency. Three rest endpoint in tihs section:

    - book/allBookInStock: Listed all books ,n the bookstore with stock quantity.
    - book/addBook:It is used for add book in the boostore system
    - book/updateStock: It is used for update stocks in terms of price and stock quantity.
    
CustomerOrderController: In this section system controls stock consistency, list all orders, add new order and list order in terms of orderr unique id. Three rest endpoint in the customer order controller:

    -order/addOrder : add order in the bookstore system. Stock consistency is checked in tihs section. If there is no book in the bookstore the system will show error messages. Customer can only be add order with their token in the session because of security issue.    
    -order/allOrders: System can show all orders again in terms token. 
    -order/{id}: Orders all details(with book detail) can view in the system with the order id and again token. 



Data Modelling: There are 4 table so as to manage in this system: customer, tokens,  orders, book

Other Informations:

    --Application hosts on Heroku as above address. Heroku connection is managed in the project application-properties files. It is shown as the following:
    --Swagger is used for rest endpoints structure. Also data modeling is in too. Swagger address is showed above.
    --In Addition to Entity jpa annotation datasource structure can also be used in tihs project. Datasource config is in config package in the project.
    --Testing: Rest methods are tested with Junit in terms of TDD
    
        
 application-properties:
 
    spring.datasource.url=jdbc:postgresql://ec2-52-212-228-71.eu-west-1.compute.amazonaws.com:5432/d6i2dfkjm15h9
    spring.datasource.username=exukgtpqpgfvhi
    spring.datasource.password=4a2cbdd727bdddca0b9327571ccdfa8ef75e25cd069c6333272232463c4b7149
    spring.jpa.show-sql=true        
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect   
    spring.jpa.hibernate.ddl-auto = update
    spring.datasource.classname =org.postgresql.Driver




    
    
