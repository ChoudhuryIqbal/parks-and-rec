
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication (scanBasePackages = {"edu.psu.sweng894.group7"})
public class ParksAndRecApplication  extends SpringBootServletInitializer{

	public SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(ParksAndRecApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ParksAndRecApplication.class, args);
		final SpringApplication application= new SpringApplication(ParksAndRecApplication.class);
		application.setBannerMode(Banner.Mode.OFF);

		application.run(args);
	}
}
