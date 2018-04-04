package soap.config;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import soap.dto.RequisitionDTO;
import soap.entity.Requisition;

/**
 * Created by EGBoldyr on 04.04.18.
 */

@Configuration
public class MapperConfig {

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(RequisitionDTO.class, Requisition.class);
            }
        };
    }

    @Bean
    public DozerBeanMapper beanMapper(BeanMappingBuilder beanMappingBuilder) {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(beanMappingBuilder);
        return mapper;
    }
}
