# Serv It
## Description
Serv It is application which can be used in every household. It allows you to add your recipes for dishes, so you have 
easy access to them. It guides you step by step how to prepare the dish you added when you need it
also it has option to mark given dish as planned - what allows you to easily export ingredients needed for preparation 
to external TODO list (<a href="https://todoist.com/">todoist.com</a>). 

## Technologies
* Java
* Spring, Spring Boot:
  * JPA
  * H2 Database
  * Lombok
  * Security with Keycloak
* Angular
* TypeScript

## Application Overview
### Home page
Home page consists of 2 buttons: 
* Displays all recipes with possibility to add new ones, plan recipe, delete or edit.
![Home](https://github.com/nitekm/Serv-It/assets/72076364/190796bb-025e-4fe2-aea2-20af779ac2f5)

### Add recipe
Add recipe allows creating new recipe.  
We can name recipe and add time, also add multiple steps and ingredients according to our needs.
![New recipe](https://github.com/nitekm/Serv-It/assets/72076364/9398d074-83e6-4518-aa26-3e616b96ac80)

After successful validation, submitting sends POST: /recipes and saves recipe in H2 database created localy on user's workstation

### Edit recipe
By clicking edit button we are able to edit our recipe thanks to PUT: /recipes/{id} with request body (updated recipe)
![Edit](https://github.com/nitekm/Serv-It/assets/72076364/89acc868-4c2a-4830-b035-3941dfbe93d5)

### Plan recipe
By clicking plan recipe button we send PATCH: /recipes/{id} which switches planned field in recipe entity on true.
We can see our planned recipes appearing in the planned section
![Planned](https://github.com/nitekm/Serv-It/assets/72076364/6a86391f-bfb1-4b5d-8149-e2bde0ad4f76)
Here we can also remove previously planned recipe from the list

### Delete recipe
By clicking delete button we simply remove recipe from our recipe list and database by sending 
DELETE: /recipes/{id}

### Shopping list
On our planned recipes list we have button, which allows us to preview our shopping list (GET: /ingredients)  

![Ingredient List](https://github.com/nitekm/Serv-It/assets/72076364/4bc4b489-a743-4f6f-b599-5f0001d0b8bb)


### Create shopping list
This option sends POST request to todoist API with every single ingredient. RestTemplate help us create valid URL 
and sends request. Shortly after we can see all our ingredients from the planned list on inbox in todoist.com or mobile app
![todoist](https://user-images.githubusercontent.com/72076364/124182211-da853c80-dab6-11eb-9eeb-732a5d629cb5.PNG)

After that app clears planned list.

