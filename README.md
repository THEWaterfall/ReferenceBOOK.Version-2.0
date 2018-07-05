# Reference book for employeers version 2.0
<i>The application was created for learning purposes.</i>

It is the the version which uses Hibernate to operate MYSQL DB. Moreover, added service layer for business logic.<br>
In general, application now has 3 layers to use database:
1. Util to create session factory, operate sessions and transactions 
2. DAO to perform CRUD and specific operations
3. Service to fulfil CRUD and specific operations with business logic

During the creation I was using the following things: 
* Java 
* JavaFX
* MYSQL Database
* JDBC
* Hibernate <b>[NEW]</b>
* MVC Pattern
* DAO Layer
* Util Layer
* Service Layer
<img src="https://github.com/THEWaterfall/ReferenceBOOK/raw/master/mainScreen.png" alt="main menu" width="500" height ="650">

The list of interaction with program *(The main window is automatically updated after performing the below features)*:
1. Click **Show Table** to fill the table with Database data
2. Click **Add** to open a new window where you can create and add new person into the Database
3. Click **Delete** to delete the existed person form the Database
4. Click **Edit** to open a new window where you can edit every info about the person except his ID.
5. Click **View** to see the full information about the person.
6. Enter ID in search text field and click **Search** to search person by ID.

*In addition*, you can 
1. Double click the person to see full information about him in another window.
2. Right click the person to open context menu with **Show**, **Edit** and **Delete** menu items.
