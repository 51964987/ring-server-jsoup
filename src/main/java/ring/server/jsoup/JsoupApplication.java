package ring.server.jsoup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages="ring")
@ServletComponentScan // 扫描使用注解方式的servlet
public class JsoupApplication extends SpringBootServletInitializer {

    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JsoupApplication.class);
	}

	public static void main(String[] args) {
        SpringApplication.run(JsoupApplication.class, args);
    }
}