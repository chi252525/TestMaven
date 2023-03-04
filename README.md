# mealorder_front_mobilej 
使用SpringMVC+Spring+Hibernate的版本

## Frameworks and Tools

* Java&IDE: JDK8 EclipsePhoton
* Backend:  SpringMVC3.2.8 Spring3.2.8 Hibernate4.2.11 (Configured by annotation)
* Frontend: Javascript JQuery Ajax (Client-side and server-side communicated by JSON)
* Database: SqlServer2014 Express
* Web Server: Tomcat 9
* Build Tool: Maven
* Windows：Win10
* Other:C3p0(database connection pool) JUnit Log4j Jackson FastJson 
# TestMaven
使用maven搭建SSH專案(spring+springmvc+Hibernate)

SqlServer sql
```
CREATE TABLE [dbo].AA(
Id [int]  not NULL,
	userName [nvarchar](128)  NULL,
	password [nvarchar](256) NULL,
	
CONSTRAINT [PK_dbo.AA] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] 

GO

```
Tomcat content.xml
```
<Resource auth="Container" driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver" maxIdle="10" maxTotal="20" maxWaitMillis="-1" name="jdbc/SQLServer2" password="54508588" type="javax.sql.DataSource" url="jdbc:sqlserver://localhost:1433;databaseName=Test" username="sa"/>
```
參考:https://www.itread01.com/content/1545809893.html

## pom.xml
```
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<groupId>com.va7</groupId>
	<artifactId>TestMaven</artifactId>
	<packaging>war</packaging>
	<version>0.0.1-SNAPSHOT</version>
	<name>TestMaven Maven Webapp</name>
	<url>http://maven.apache.org</url>
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<spring.version>4.2.6.RELEASE</spring.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>3.8.1</version>
			<scope>test</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
			<scope>provided</scope>
		</dependency>
		<!-- https://mvnrepository.com/artifact/javax.servlet/jsp-api -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.0</version>
			<scope>provided</scope>
		</dependency>
		<!-- 引入資料庫連線池 -->
		<dependency>
			<groupId>com.mchange</groupId>
			<artifactId>c3p0</artifactId>
			<version>0.9.5-pre10</version>
		</dependency>
		<!-- springframework 4 dependencies begin -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>${spring.version}</version>
		</dependency>
		<!-- springframework 4 dependencies end -->

		<!-- hibernate 配置 begin -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>3.6.9.Final</version>
		</dependency>
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>3.6.9.Final</version>
		</dependency>
		<!-- hibernate 配置 end -->

		<!-- mysql資料庫的驅動包 start -->
		<dependency>
			<groupId>com.microsoft.sqlserver</groupId>
			<artifactId>mssql-jdbc</artifactId>
			<version>6.1.0.jre8</version>
		</dependency>

		<!-- 引入jstl包 -->
		<dependency>
			<groupId>jstl</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
		<!-- standard.jar -->
		<dependency>
			<groupId>taglibs</groupId>
			<artifactId>standard</artifactId>
			<version>1.1.2</version>
		</dependency>
	</dependencies>
	<build>
		<finalName>TestMaven</finalName>
		<plugins>
			<plugin>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.5.1</version>
				<configuration>
					<source>1.8</source>
					<target>1.8</target>
				</configuration>
			</plugin>
			<plugin>
				<artifactId>maven-war-plugin</artifactId>
				<version>3.2.1</version>
				<configuration>
					<warSourceDirectory>WebContent</warSourceDirectory>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>


```
## web.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://java.sun.com/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
          http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
    version="3.0">
   <display-name>Servlet 3.0 Web Application</display-name>
 <!-- 配置spring容器 -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring.xml</param-value>
    </context-param>

    <!-- 配置監聽器 -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <!-- 請求都交給DispatcherServlet處理 -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-mvc.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>

    <!-- 增加中文亂碼過濾器 -->
    <filter>
        <filter-name>CharacterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>CharacterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 清除jsp空格 -->
    <jsp-config>
        <jsp-property-group>
            <url-pattern>*.jsp</url-pattern>
            <trim-directive-whitespaces>true</trim-directive-whitespaces>
        </jsp-property-group>
    </jsp-config>
</web-app>
```
## org.eclipse.wst.common.project.facet.core.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<faceted-project>
  <runtime name="Apache Tomcat v9.0"/>
  <fixed facet="wst.jsdt.web"/>
  <installed facet="wst.jsdt.web" version="1.0"/>
  <installed facet="java" version="1.8"/>
  <installed facet="jst.jaxrs" version="1.1"/>
  <installed facet="jst.web" version="4.0"/>
</faceted-project>

```
