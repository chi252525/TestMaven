package com.web.util;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
 
@Configuration
@PropertySource(value="classpath:application.properties") 
public class Configproperties {
 
@Value("${app.takeinfo}")
public String TAKEINFO;

@Value("${app.orderQrcode}")
public String ORDERQRCODE;

@Value("${app.orderTimeOut}")
public String ORDERTIMEOUT;

@Value("${app.taxType}")
public String TAXTYPE;
}