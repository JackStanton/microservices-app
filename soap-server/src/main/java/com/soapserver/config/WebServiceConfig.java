package com.soapserver.config;

import org.hibernate.cfg.annotations.reflection.internal.XMLContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import ru.dstu.generated.NameSpace;

@EnableWs
@Configuration
@EnableJpaRepositories("com.soapserver.repository")
@EntityScan("ru.dstu.generated")
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

//    @Bean
//    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema xsdSchema){
//        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//        wsdl11Definition.setPortTypeName("UsersPort");
//        wsdl11Definition.setLocationUri("/ws");
//        wsdl11Definition.setTargetNamespace(new NameSpace().getNameSpace());
//        wsdl11Definition.setSchema(xsdSchema);
//        return wsdl11Definition;
//    }
//
//    @Bean
//    public XsdSchema xsdSchema(){
//        return new SimpleXsdSchema(new ClassPathResource("schema.xsd"));
//    }
}
