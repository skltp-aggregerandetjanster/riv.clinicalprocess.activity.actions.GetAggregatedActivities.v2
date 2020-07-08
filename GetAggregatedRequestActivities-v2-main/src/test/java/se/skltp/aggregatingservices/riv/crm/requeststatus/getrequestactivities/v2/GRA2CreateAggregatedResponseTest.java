package se.skltp.aggregatingservices.riv.crm.requeststatus.getrequestactivities.v2;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.crm.requeststatus.getrequestactivitiesresponder.v2.GetRequestActivitiesResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateAggregatedResponseTest;
import se.skltp.aggregatingservices.data.TestDataGenerator;


@RunWith(SpringJUnit4ClassRunner.class)
public class GRA2CreateAggregatedResponseTest extends CreateAggregatedResponseTest {

  private static GRA2AgpServiceConfiguration configuration = new GRA2AgpServiceConfiguration();
  private static AgpServiceFactory<GetRequestActivitiesResponseType> agpServiceFactory = new GRA2AgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GRA2CreateAggregatedResponseTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @Override
  public int getResponseSize(Object response) {
    GetRequestActivitiesResponseType responseType = (GetRequestActivitiesResponseType) response;
    return responseType.getRequestActivity().size();
  }
}