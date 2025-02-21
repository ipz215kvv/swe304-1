# **SWE 304 Group Project - 1**  

## **🛠 Prerequisites**  

Make sure you have the following installed on your system:

- **Java 17** ([Download](https://adoptium.net/))
- **Maven** ([Install Guide](https://maven.apache.org/install.html))
- **MySQL Server** ([Download](https://dev.mysql.com/downloads/))


## **📂 Installation**  

1. **Clone the repository**  
    ```bash
    git clone https://github.com/ipz215kvv/swe304-1.git
    cd swe304-1
    ```

2. **`.env` file**  
    Create a `.env` file in the root directory and populate it with variables like showed in [example](.env.example).

3. **Create database locally**  
    If MySQL is installed locally, create the database manually, all required tables will be generated automatically:

    ```sql
    CREATE DATABASE swe304;
    ```


## **🚀 Build & Run the Project**  

1. **Build the `.jar` file**  
    ```bash
    mvn clean package
    ```

2. **Run the application**  
    ```bash
    java -jar target/swe304_1-0.0.1-SNAPSHOT.jar --server.port=8000
    ```

3. **Access the Web App**  
    Once running, open your browser and go to:  
    👉 [http://localhost:8000](http://localhost:8000)