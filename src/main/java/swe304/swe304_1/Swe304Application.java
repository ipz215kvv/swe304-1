package swe304.swe304_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class Swe304Application {

	public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
			setSystemProperty("MYSQL_HOST", dotenv.get("MYSQL_HOST"));
			setSystemProperty("MYSQL_PORT", dotenv.get("MYSQL_PORT"));
			setSystemProperty("MYSQL_DATABASE", dotenv.get("MYSQL_DATABASE"));
        } catch (Exception e) {
			
		}
		

		SpringApplication.run(Swe304Application.class, args);
	}

	private static void setSystemProperty(String key, String value) {
        if (System.getProperty(key) == null && value != null) {
            System.setProperty(key, value);
        }
    }

}
