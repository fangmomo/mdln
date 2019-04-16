package mysb.mdln.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"mysb.mdln.respositroy"})
@EntityScan(basePackages = {"mysb.mdln.domain"})
public class MdlnConfig {

}
