# myRetail RESTful Product Services
This project contains two services - Get Product Price and Update Product Price. Spring Boot has been used to develop the REST services. These services internally call MongoDB to fetch and insert product data. Technology stack of the codebase is given below:

•	Java 1.7
•	Spring BOOT 1.4.3
•	Mockito
•	Junit
•	MongoDB
•	Maven 3.3
•	Tomcat 8
•	Docker

# Deployment Instructions

## Create MongoDB Container

Get MongoDB image from DockerHub. Run MongoDB container with name myRetailDB. Create DB myretail and collection productprice inside the container. Insert sample rows in the collection as below:
db.productprice.insert({_id:13860428,price:100.13,code:”USD”})
db.productprice.insert({_id:13860421,price:10.13,code:”USD”})
db.productprice.insert({_id:13860422,price:9.13,code:”USD”})
db.productprice.insert({_id:13860423,price:18.13,code:”USD”})
db.productprice.insert({_id:13860424,price:109.14,code:”USD”})
db.productprice.insert({_id:13860425,price:101.13,code:”USD”})
db.productprice.insert({_id:13860426,price:102.13,code:”USD”})
db.productprice.insert({_id:13860427,price:103.13,code:”USD”})

## Build Code

Clone or download the code from GIT. In the code download directory, execute the below Maven command to package the code:
mvn –U clean install
Above will create WAR file productDetails-0.0.1-SNAPSHOT.war in target directory. 

## Create Code Container

Copy WAR file productDetails-0.0.1-SNAPSHOT.war and Dockerfile in a directory. Run the below docker command to create the image:
docker build --rm --no-cache -t myretail:1.0 .

myretail image will be created. Now run the image by linking MongoDB container myRetailDB.

docker run -p 8080:8080 -e spring.profiles.active=prod -e spring.data.mongodb.uri=mongodb:// myRetailDB:27017/myretail  -v /logs:/logs —link myRetailDB: myRetailDB —name myretail myretail:1.0




