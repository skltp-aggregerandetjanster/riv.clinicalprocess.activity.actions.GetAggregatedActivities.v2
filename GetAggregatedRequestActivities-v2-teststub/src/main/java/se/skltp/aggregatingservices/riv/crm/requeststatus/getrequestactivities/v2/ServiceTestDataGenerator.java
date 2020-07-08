package se.skltp.aggregatingservices.riv.crm.requeststatus.getrequestactivities.v2;

import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Service;
import riv.crm.requeststatus._2.AccessControlHeaderType;
import riv.crm.requeststatus._2.AuthorType;
import riv.crm.requeststatus._2.CVType;
import riv.crm.requeststatus._2.HeaderType;
import riv.crm.requeststatus._2.IIType;
import riv.crm.requeststatus._2.RequestActivityBodyType;
import riv.crm.requeststatus._2.RequestActivityType;
import riv.crm.requeststatus.getrequestactivitiesresponder.v2.GetRequestActivitiesResponseType;
import riv.crm.requeststatus.getrequestactivitiesresponder.v2.GetRequestActivitiesType;
import se.skltp.aggregatingservices.data.TestDataGenerator;

@Log4j2
@Service
public class ServiceTestDataGenerator extends TestDataGenerator {

	@Override
	public String getPatientId(MessageContentsList messageContentsList) {
		GetRequestActivitiesType request = (GetRequestActivitiesType) messageContentsList.get(1);
		return request.getPatientId().getExtension();
	}

	@Override
	public Object createResponse(Object... responseItems) {
		log.info("Creating a response with {} items", responseItems.length);
		GetRequestActivitiesResponseType response = new GetRequestActivitiesResponseType();
		for (int i = 0; i < responseItems.length; i++) {
			response.getRequestActivity().add((RequestActivityType)responseItems[i]);
		}

		log.info("response.toString:" + response.toString());

		return response;
	}

	@Override
	public Object createResponseItem(String logicalAddress, String registeredResidentId, String businessObjectId, String time) {
		log.debug("Created ResponseItem for logical-address {}, registeredResidentId {} and businessObjectId {}",
				new Object[]{logicalAddress, registeredResidentId, businessObjectId});

		RequestActivityType response = new RequestActivityType();

		RequestActivityBodyType body = new RequestActivityBodyType();
		response.setBody(body);

		HeaderType header = new HeaderType();
		response.setHeader(header);

		AccessControlHeaderType ach = new AccessControlHeaderType();
		header.setAccessControlHeader(ach);
		AuthorType author = new AuthorType();
		header.setAuthor(author );
		header.setSourceSystemId(createIIType("", logicalAddress));

		author.setName("The Author");
		author.setTimestamp("20200101151300");

		ach.setOriginalPatientId(createIIType("", registeredResidentId));
		ach.setAccountableCareGiver(createIIType("root","AccountableCareGiver"));
		ach.setAccountableCareUnit(createIIType("root","AccountableCareUnit"));
		ach.setApprovedForPatient(true);
		ach.setCareProcessId(businessObjectId);

		body.setStatusCode(createCV("1.2.752.129.2.2.2.43", "7"));
		body.setEventTime("20200115153000");

		return response;
	}

	public Object createRequest(String patientId, String sourceSystemHSAId){
		GetRequestActivitiesType outcomeType = new GetRequestActivitiesType();
		outcomeType.setSourceSystemHSAId(createIIType("",sourceSystemHSAId));
		outcomeType.setPatientId(createIIType("1.2.752.129.2.1.3.1", patientId));
		return outcomeType;
	}

	private IIType createIIType(String root, String extension) {
		IIType iiType = new IIType();
		iiType.setRoot(root);
		iiType.setExtension(extension);
		return iiType;
	}

	public static CVType createCV(String oid, String code) {
		CVType cv = new CVType();
		cv.setCode(code);
		cv.setCodeSystem(oid);
		return cv;
	}
}
