import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan (basePackages= { "edu.psu.sweng894.group7" })
@SpringBootApplication
@EntityScan(basePackages = {"edu.psu.sweng894.group7.datastore.entity"})
@lombok.Generated
public class ParksAndRecApplication {
		public static void main(String[] args) {
			SpringApplication.run(ParksAndRecApplication.class, args);
		}
}
