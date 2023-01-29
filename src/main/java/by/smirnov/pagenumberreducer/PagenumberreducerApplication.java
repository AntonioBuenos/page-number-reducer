package by.smirnov.pagenumberreducer;

import by.smirnov.pagenumberreducer.configuration.OpenAPIConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "by.smirnov.pagenumberreducer")
@Import({OpenAPIConfig.class})
public class PagenumberreducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagenumberreducerApplication.class, args);
	}

}
