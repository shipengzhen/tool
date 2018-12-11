<?xml version="1.0" encoding="UTF-8"?>
<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

<!-- 
      desc: public config
      template version: 1.0
      author:  zyf
      date  :2010-01-20
 -->
	<bean id="dataSource"
		class="org.apache.commons.dbcp.BasicDataSource">
		<property name="driverClassName"
			value="${driver}">
		</property>
		<property name="url"
			value="${url}">
		</property>
		<property name="username" value="${user}"></property>
		<property name="password" value="${pass}"></property>
	</bean>
</beans>