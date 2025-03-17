package swe304.swe304_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class Swe304Application {

	public static void main(String[] args) {
        try {
            Dotenv dotenv = Dotenv.load();
			
			setSystemProperty("MYSQL_USER", dotenv.get("MYSQL_USER"));
			setSystemProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));
			setSystemProperty("MYSQL_HOST", dotenv.get("MYSQL_HOST"));
			setSystemProperty("MYSQL_PORT", dotenv.get("MYSQL_PORT"));
			setSystemProperty("MYSQL_DATABASE", dotenv.get("MYSQL_DATABASE"));
			
			setSystemProperty("AWS_ACCESS_KEY", dotenv.get("AWS_ACCESS_KEY"));
			setSystemProperty("AWS_SECRET_KEY", dotenv.get("AWS_SECRET_KEY"));
			setSystemProperty("AWS_S3_REGION", dotenv.get("AWS_S3_REGION"));
			setSystemProperty("AWS_S3_BUCKET_NAME", dotenv.get("AWS_S3_BUCKET_NAME"));
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
