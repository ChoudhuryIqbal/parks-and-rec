
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("edu.psu.sweng894.group7")
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
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
