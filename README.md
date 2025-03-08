# **SWE 304 Group Project - 1**

## **🛠 Prerequisites**

Make sure you have the following installed on your system:

- **Java 17** ([Download](https://adoptium.net/))
- **Gradle** ([Install Guide](https://gradle.org/install/))
- **MySQL Server** ([Download](https://dev.mysql.com/downloads/))

## **📂 Installation**

1. **Clone the repository**

    ```bash
    git clone https://github.com/ipz215kvv/swe304-1.git
    cd swe304-1
    ```

2. **`.env` file**

    Create a `.env` file in the root directory and populate it with variables like shown in the [example](.env.example).

3. **Create database locally**

    If MySQL is installed locally, create the database manually, all required tables will be generated automatically:

    ```sql
    CREATE DATABASE swe304;
    ```

## **🚀 Build & Run the Project**

1. **Build the project**

    ```bash
    gradle clean build
    ```

2. **Run the application**

    ```bash
    java -jar build/libs/swe304_1-1.0.0.jar --server.port=8080
    ```

    or

    ```bash
    gradle run
    ```

3. **Access the Web App**

    Once running, open your browser and go to:  
    👉 [http://localhost:8080](http://localhost:8080)

## **🧳 Migrating from Maven to Gradle**

When you pull the commit replacing Maven with Gradle, follow these steps to migrate the project:

1. **Remove Maven-specific files**

   Delete the `pom.xml`, `mvnw`, `mvnw`, `mvnw.cmd` files, and `.mvn/` folder, as Gradle will no longer use it.

2. **Refresh dependencies**

    Your first build execution need to refresh newly added dependencies:

    ```bash
    gradle build --refresh-dependencies
    ```
