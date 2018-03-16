package soap.config;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import soap.jaxws.AccountWebService;
import soap.jaxws.AuthorizationWebService;
import soap.jaxws.ClientWebService;

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

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus);
        endpoint.publish("/authorization", authorizationWS);
        endpoint.publish("/clients", clientWS);
        endpoint.publish("/accounts", accountWS);
        return endpoint;
    }
}
