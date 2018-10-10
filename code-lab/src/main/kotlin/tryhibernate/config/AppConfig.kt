package tryhibernate.config

import com.alibaba.druid.pool.DruidDataSource
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.hibernate.SessionFactory
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder
import tryhibernate.module.TryIoModule
import java.util.*

@Configuration
@ComponentScan(basePackages = ["tryhibernate.domain", "tryhibernate.module", "tryhibernate.dao", "tryhibernate.service"])
@PropertySource(value = ["classpath:jdbc.properties"])
@EnableTransactionManagement
@EnableScheduling
open class AppConfig {

    @Autowired
    private lateinit var environment: Environment

    @Bean(initMethod = "init", destroyMethod = "close")
    open fun dataSource(): DruidDataSource {
        // String jdbcUrl = environment.getProperty("jdbc.url");
        val jdbcUrl = environment.getProperty("jdbc.url")
//		System.out.println(jdbcUrl);

        val jdbcUserName = environment.getProperty("jdbc.username")
        val jdbcPwd = environment.getProperty("jdbc.password")

        val dataSource = DruidDataSource()
        dataSource.setUrl(jdbcUrl)
        dataSource.setUsername(jdbcUserName)
        dataSource.setPassword(jdbcPwd)
        dataSource.setInitialSize(1)
        dataSource.setMaxActive(20)

        return dataSource
    }

    @Bean
    open fun sessionFactory(): SessionFactory {
        System.out.println("2 " + environment)

        val localSessionFactoryBuilder = LocalSessionFactoryBuilder(dataSource())
        localSessionFactoryBuilder.scanPackages("tryhibernate.domain")

        val properties = Properties()
        properties.put("hibernate.hbm2ddl.auto", "create");

        val hibernateDialect = environment.getProperty("hibernate.dialect")
        properties.put("hibernate.dialect", hibernateDialect)
        // properties.put("hibernate.dialect",
        // "org.hibernate.dialect.MySQLDialect");

        // properties.put("hibernate.show_sql", true);
        properties.put("hibernate.format_sql", false)
        properties.put("hibernate.use_sql_comments", false)
        properties.put("hibernate.cache.use_second_level_cache", false)

        localSessionFactoryBuilder.addProperties(properties)
        return localSessionFactoryBuilder.buildSessionFactory()
    }

    @Bean
    open fun transactionManager(): HibernateTransactionManager {
        val hibernateTransactionManager = HibernateTransactionManager()
        val ss = sessionFactory()
        hibernateTransactionManager.sessionFactory = ss
        return hibernateTransactionManager
    }

    @Bean
    open fun tryIoModule(): TryIoModule {
        val tryIoModule = TryIoModule()
        return tryIoModule
    }
}