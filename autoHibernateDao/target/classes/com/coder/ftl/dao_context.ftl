<?xml version="1.0" encoding="UTF-8"?>
<beans
	xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">

<!-- 
      desc: config dao Bean
      template version: 1.0
      author:  zyf
      date  :2010-01-20
 -->
<#list xmlfile as o>
	<bean id="${o.className}Dao" class="${o.pagckdir}.impl.${o.className}DaoImpl">
		<property name="sessionFactory">
			<ref bean="SessionFactory" />
		</property>
	</bean>
</#list>
</beans>