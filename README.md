# **SWE 304 Group Project**

Our Postman [project](https://www.postman.com/iamkorniichuk/swe304/).

## **🛠 Prerequisites**

Make sure you have the following installed on your system:

- **Java 17** ([Download](https://adoptium.net/))
- **Gradle** ([Install Guide](https://gradle.org/install/))
- **Docker** ([Download](https://www.docker.com/products/docker-desktop/))

## **📂 Installation**

1. **Clone the repository**

    ```bash
    git clone https://github.com/ipz215kvv/swe304-1.git
    cd swe304-1
    ```

2. **`.env` file**

    Create a `.env` file in the root directory and populate it with variables like shown in the [example](.env.example).

3. **Build `.jar` file**

    ```bash
    gradle clean build
    ```

4. **Build docker image**

    Firstly, start your docker service, then run:

    ```bash
    docker compose up --build
    ```

5. **Access the Web App**

    Once running, open your browser and go to:  
    👉 [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

    You will see the project scheme here.


## **🧳 Migrating from Maven to Gradle**

When you pull the commit replacing Maven with Gradle, follow these steps to migrate the project:

1. **Remove Maven-specific files**

   Delete the `pom.xml`, `mvnw`, `mvnw`, `mvnw.cmd` files, and `.mvn/` folder, as Gradle will no longer use it.

2. **Refresh dependencies**

    Your first build execution need to refresh newly added dependencies:

    ```bash
    gradle build --refresh-dependencies
    ```
