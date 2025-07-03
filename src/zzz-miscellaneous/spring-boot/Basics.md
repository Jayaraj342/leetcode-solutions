Spring Boot is an extension of the Spring framework, which eliminates the boilerplate configurations required for setting up a Spring application

### Key Characteristics:

`Autoconfiguration`: Reduces the need for manual setup.  
`Opinionated defaults`: Gets you started quickly.(Springboot starter web gives - Dependency resolution, Avoid version conflicts)  
`Embedded servers`: Like Tomcat or Jetty, so you don’t need to deploy WAR files.  
`Spring Boot Starter dependencies`: Easy inclusion of required libraries.  
`Production-ready`: Built-in metrics, health checks, etc... (Actuator, micrometer)

`@SpringBootApplication` is the entry point of a Spring Boot app
```
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Inherited

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = {@Filter(type = CUSTOM, classes = TypeExcludeFilter.class)})
public @interface SpringBootApplication
```

`@SpringBootConfiguration`: Marks the class as a source of bean definitions for the application context  
<details>
<summary>more...</summary>
In Spring, a bean is simply an object that is managed by the Spring IoC (Inversion of Control) container. These objects are created, assembled, and managed by the container — not manually by your code   

`SpringBootConfiguration`:This class is a configuration class. Treat it as a source of bean definitions (If not it won't scan the classes)  

</details>

`@EnableAutoConfiguration`: Based on the libraries on your classpath (like Spring MVC, JPA, etc.), Spring Boot auto-configures your application   
<details>
<summary>more...</summary>
No additional configuration required as in Spring.  
ex : If we Add spring data JPA - Inside application-context.xml
- should add bean of Datasource with required properties
- bean of session factory, Inject datasource to session factory
- define all hibernate properties
- create transaction manager, inject session factory into it

https://github.com/Java-Techie-jt/Payment-Resource/blob/master/src/main/webapp/WEB-INF/application-context.xml
```
<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/test" />
		<property name="username" value="root" />
		<property name="password" value="cisco" />
	</bean>
	<bean id="sessionFactory"
		class="org.springframework.orm.hibernate4.LocalSessionFactoryBean">
		<property name="dataSource" ref="dataSource" />
		<property name="annotatedClasses">
			<list>
				<value>com.spring.rest.curd.model.Payment</value>
			</list>
		</property>
		<property name="hibernateProperties">
			<props>
				<prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
				<prop key="hibernet.show_sql">true</prop>
				<prop key="hibernate.hbm2ddl.auto">update</prop>
			</props>
		</property>
	</bean>
	<bean id="transactionManager"
		class="org.springframework.orm.hibernate4.HibernateTransactionManager">
		<property name="sessionFactory" ref="sessionFactory" />
	</bean>
```

### debug = true
=> Gives positive and negative matches
=> If Object mapper is found, Autoconfigure from this class (Positive matches)
```
@AutoConfiguration
@ConditionalOnClass(com.fasterxml.jackson.databind.ObjectMapper.class)
public class JacksonAutoConfiguration
```
=> If Spring-boot data JPA is not there then DataSourceAutoConfiguration comes under negative matches
=> If its there, then positive matches (App start will fail if no db config is provided)
</details>

* XML - scan the classes
* Kickoff Dispatcher servlet manually - Web.xml

`@ComponentScan`: Automatically scans the current package and all sub-packages for @Component, @Service etc... 
=> If not scanned, classes outside root package won't be scanned  

### `Servlet`
Servlet is a Java class used to handle HTTP requests and generate responses — typically in a web application. 
It is part of the Java EE (Jakarta EE) Servlet API and runs on a servlet container like Tomcat
```
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().write("Hello, World!");
    }
}
```

<details>
<summary>more...</summary>
* Servlet Lifecycle   
init() – called once when the servlet is initialized   
service() – called each time a request is received   
destroy() – called when the servlet is taken out of service   

* A Servlet Container is a part of the web server (like Tomcat) that:  
Loads servlet classes  
Manages their lifecycle  
Maps URLs to servlets  
Handles HTTP communication  

* Behind the scenes:  
Spring creates a servlet (like DispatcherServlet)  
That servlet routes your request to the appropriate controller method  
So Spring simplifies things, but it's still built on top of servlets  

* Summary  
Servlet	A Java class that handles HTTP requests/responses  
Servlet Container	A server (like Tomcat) that runs servlets  
In Spring	You don’t write servlets directly — Spring’s DispatcherServlet handles it  
</details>

`public static void main`: SpringApplication.run  
* prepare environment variables  
* create Application context - using Servlet Web Application Context factory (Web application type is servlet, because of starter web)  
* Register bean into context  
* start tomcat (since it servlet - it won't start jetty or undertow server)  

`StereoType Annotations`: Controller, Service, Repository - inherited from Component - From which Beans will be created  

`Dependency Injection`: Achieve loose coupling (with interfaces) - Springframework injects dependency (Autowired or Constructor or Setter - Constructor Mandatory Bean, while Setter Bean is not mandatory)  
(Runtime polymorphism achieves partial loose coupling - as Interface is used in reference)  

`Circular dependency`: Use setter injection and Lazy init for constructor - no more errors...  

`Post construct`: Method that needs to be called after Bean init. Used to set default values, create connections etc...  

`@Value Annotation`: get property dynamically (Other option : use Environment Bean or command line args)  

`@ConfigurationProperties Annotation`: map config file to Java object   

`@Qualifier vs @Primary Annotation`: Bean conflict resolution - use Resource too i.e. specific to java  

`Bean scope ex : @Scope("prototype") - context.getBean(ABC.class)`: Determines lifecycle of bean  
* Singleton - single instance (Single object in Application context/spring container - Default scope)  
* Prototype - new everytime we request (use : user session, thread safety)  
* Request - per http request  
* Session - per session till timeout  
* Application - per Application context  

`Can we inject prototype bean in singleton bean ?`: Yes, prototype bean will be created only once  
=> To create multiple times, use context.getBean or factory to get the object of prototype class  

