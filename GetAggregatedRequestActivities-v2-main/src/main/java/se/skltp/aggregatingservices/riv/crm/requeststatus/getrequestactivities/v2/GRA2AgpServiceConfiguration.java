package se.skltp.aggregatingservices.riv.crm.requeststatus.getrequestactivities.v2;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import riv.crm.requeststatus.getrequestactivities.v2.rivtabp21.GetRequestActivitiesResponderInterface;
import riv.crm.requeststatus.getrequestactivities.v2.rivtabp21.GetRequestActivitiesResponderService;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "getaggregatedrequestactivities.v2")
public class GRA2AgpServiceConfiguration extends se.skltp.aggregatingservices.configuration.AgpServiceConfiguration {

public static final String SCHEMA_PATH = "/schemas/TD_REQUESTSTATUS_20200218/interactions/GetRequestActivitiesInteraction/GetRequestActivitiesInteraction_2.0_RIVTABP21.wsdl";

  public GRA2AgpServiceConfiguration() {

    setServiceName("GetAggregatedRequestActivities-v2");
    setTargetNamespace("urn:riv:crm:requeststatus:GetRequestActivities:2:rivtabp21");

    // Set inbound defaults
    setInboundServiceURL("http://0.0.0.0:9027/GetAggregatedRequestActivities/service/v2");
    setInboundServiceWsdl(SCHEMA_PATH);
    setInboundServiceClass(GetRequestActivitiesResponderInterface.class.getName());
    setInboundPortName(GetRequestActivitiesResponderService.GetRequestActivitiesResponderPort.toString());

    // Set outbound defaults
    setOutboundServiceWsdl(SCHEMA_PATH);
    setOutboundServiceClass(getInboundServiceClass());
    setOutboundPortName(getInboundPortName());

    // FindContent
    setEiServiceDomain("riv:crm:requeststatus");
    setEiCategorization("req-act");

    // TAK
    setTakContract("urn:riv:crm:requeststatus:GetRequestActivitiesResponder:2");

    // Set service factory
    setServiceFactoryClass(GRA2AgpServiceFactoryImpl.class.getName());
    }


}
