package solar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Merli {
  private static Logger logger = LoggerFactory.getLogger(Merli.class);


    public static void main(String[] args) {
        logger.info("Starting Merli");
        SpringApplication.run(Merli.class, args);
    }
}
