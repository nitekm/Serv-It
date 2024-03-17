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
* Add recipe button redirecting to GET: /recipes/add
* Show all recipes button redirecting to GET: /recipes    
  

![Przechwytywanie](https://user-images.githubusercontent.com/72076364/124179675-744aea80-dab3-11eb-804e-5098dd4b169d.PNG)

### Add recipe
Add recipe allows creating new recipe.  
We can name recipe and add time, also add multiple steps and ingredients according to our needs.
![add recipe](https://user-images.githubusercontent.com/72076364/124180332-55992380-dab4-11eb-86bf-ffecb4866687.PNG)

Form has also field validation and will not let us save recipe with invalid data  
![add recipe validation](https://user-images.githubusercontent.com/72076364/124180346-592caa80-dab4-11eb-9cee-39d974abb9ec.PNG)

After successful validation, submitting sends POST: /recipes and saves recipe in H2 database created localy on user's workstation

### Show all recipes
This page presents all recipes we added (GET: /recipes), also at the top we have fieldset with our planned dishes.
![all recipes](https://user-images.githubusercontent.com/72076364/124182168-cccfb700-dab6-11eb-9d13-b4fe580a366e.PNG)

### Recipe preview
By clicking on recipe name we send GET: /recipes/{id} and we get recipe preview
![recipe preview ](https://user-images.githubusercontent.com/72076364/124182194-d5c08880-dab6-11eb-8e47-1351f7636499.PNG)

### Edit recipe
By clicking edit button we are able to edit our recipe thanks to PUT: /recipes/{id} with request body (updated recipe)
![edit recipe](https://user-images.githubusercontent.com/72076364/124182180-cfcaa780-dab6-11eb-865f-169b1ae0a14d.PNG)

### Plan recipe
By clicking plan recipe button we send PATCH: /recipes/{id} which switches planned field in recipe entity on true.
We can see our planned recipes appearing in the planned section
![planned](https://user-images.githubusercontent.com/72076364/124182186-d2c59800-dab6-11eb-8fd0-77b0ecc0d8a1.PNG)
Here we can also remove previously planned recipe from the list

### Delete recipe
By clicking delete button we simply remove recipe from our recipe list and database by sending 
DELETE: /recipes/{id}

### Shopping list
On our planned recipes list we have button, which allows us to preview our shopping list (GET: /ingredients)  

![shopping list](https://user-images.githubusercontent.com/72076364/124182203-d822e280-dab6-11eb-9b96-aa1c8e75944a.PNG)


### Create shopping list
This option sends POST request to todoist API with every single ingredient. RestTemplate help us create valid URL 
and sends request. Shortly after we can see all our ingredients from the planned list on inbox in todoist.com or mobile app
![todoist](https://user-images.githubusercontent.com/72076364/124182211-da853c80-dab6-11eb-9eeb-732a5d629cb5.PNG)

After that app clears planned list.

