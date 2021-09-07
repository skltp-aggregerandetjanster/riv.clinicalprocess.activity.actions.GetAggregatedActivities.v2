package se.skltp.aggregatingservices.riv.crm.requeststatus.getrequestactivities.v2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import riv.crm.requeststatus.getrequestactivitiesresponder.v2.GetRequestActivitiesResponseType;
import se.skltp.aggregatingservices.api.AgpServiceFactory;
import se.skltp.aggregatingservices.tests.CreateFindContentTest;
import se.skltp.aggregatingservices.data.TestDataGenerator;


@ExtendWith(SpringExtension.class)
public class GRA2CreateFindContentTest extends CreateFindContentTest {

  private static GRA2AgpServiceConfiguration configuration = new GRA2AgpServiceConfiguration();
  private static AgpServiceFactory<GetRequestActivitiesResponseType> agpServiceFactory = new GRA2AgpServiceFactoryImpl();
  private static ServiceTestDataGenerator testDataGenerator = new ServiceTestDataGenerator();

  public GRA2CreateFindContentTest() {
    super(testDataGenerator, agpServiceFactory, configuration);
  }

  @BeforeAll
  public static void before() {
    configuration = new GRA2AgpServiceConfiguration();
    agpServiceFactory = new GRA2AgpServiceFactoryImpl();
    agpServiceFactory.setAgpServiceConfiguration(configuration);
  }


}
