package se.skltp.aggregatingservices.riv.crm.requeststatus.getrequestactivities.v2;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import riv.crm.requeststatus.getrequestactivitiesresponder.v2.GetRequestActivitiesResponseType;
import riv.crm.requeststatus.getrequestactivitiesresponder.v2.GetRequestActivitiesType;
import se.skltp.aggregatingservices.AgServiceFactoryBase;

@Log4j2
public class GRA2AgpServiceFactoryImpl extends
    AgServiceFactoryBase<GetRequestActivitiesType, GetRequestActivitiesResponseType> {

  @Override
  public String getPatientId(GetRequestActivitiesType queryObject) {
    return queryObject.getPatientId().getExtension();
  }

  @Override
  public String getSourceSystemHsaId(GetRequestActivitiesType queryObject) {
    return queryObject.getSourceSystemHSAId() == null ? null : queryObject.getSourceSystemHSAId().getExtension();
  }

  @Override
  public GetRequestActivitiesResponseType aggregateResponse(List<GetRequestActivitiesResponseType> aggregatedResponseList) {

    GetRequestActivitiesResponseType aggregatedResponse = new GetRequestActivitiesResponseType();

    for (Object object : aggregatedResponseList) {
      GetRequestActivitiesResponseType response = (GetRequestActivitiesResponseType) object;
      aggregatedResponse.getRequestActivity().addAll(response.getRequestActivity());
    }
    log.info("Returning {} aggregated request activities v2", aggregatedResponse.getRequestActivity().size());

    return aggregatedResponse;
  }
}

