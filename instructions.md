##Instructions to run the project by Alberto García Castro:

- The whole project has been made in Java 11 version and in maven, using the compiler Intellij IDEA
so I will suggest to use the same compiler to check out the project and play with it.

- Only charge the project into the IDE as a maven project, configure the application run window
that is next to the play project buttom and run it. Then you can test the services by using the 
included swagger or using postman, as I explain below. 

##Storage data base
-As it was said, the project use an SQL data base. The script to create it is included
inside the resources folder in the project.

  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/itemdb
    username: admin
    password: root

##Test the methods
-Once the project is running, it has include a swagger 2 to test the services. 
To access this swagger, just insert the following url in your browser: http://localhost:8085/swagger-ui.html
The methods works by inserting the following url I was provices with: http://feeds.nos.nl/nosjournaal?format=xml


##Additional software
I used another different software to test my code. It is postman and if you want to test the methods by using it, you can find it here: https://www.postman.com/downloads/
The rest of the tools are included into the IDE.

