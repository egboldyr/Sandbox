package soap.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import soap.jaxws.*;

import javax.xml.ws.Endpoint;

/**
 * Created by EGBoldyr on 15.03.18.
 */

@Configuration
@EnableWs
public class EndpointWSConfig {

    @Autowired
    private Bus bus;

    @Autowired private AuthorizationWebService authorizationWS;
    @Autowired private ClientWebService clientWS;
    @Autowired private AccountWebService accountWS;
    @Autowired private RequisitionWebService requisitionWS;
    @Autowired private CourseWebService courseWS;
    @Autowired private GroupWebService groupWS;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus);
        endpoint.publish("/authorization", authorizationWS);
        endpoint.publish("/clients", clientWS);
        endpoint.publish("/accounts", accountWS);
        endpoint.publish("/requisitions", requisitionWS);
        endpoint.publish("/courses", courseWS);
        endpoint.publish("/groups", groupWS);
        return endpoint;
    }
}
