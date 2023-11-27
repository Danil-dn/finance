package fin.kda.project.service;

import fin.kda.project.dto.GetCursOnDateXML;
import fin.kda.project.dto.GetCursOnDateXmlResponse;
import fin.kda.project.dto.ValuteCursOnDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.*;


public class CentralRussianBankService extends WebServiceTemplate {

    @Value("${cb.api}")
    private String cbrApiUrl;

    public List<ValuteCursOnDate> getCurrenciesFromCbr() throws DatatypeConfigurationException {
        final GetCursOnDateXML getCursOnDateXML = new GetCursOnDateXML();
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(new Date());

        getCursOnDateXML.setOnDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));

        GetCursOnDateXmlResponse response = (GetCursOnDateXmlResponse) marshalSendAndReceive(cbrApiUrl, getCursOnDateXML);

        if (response == null) {
            throw new IllegalStateException("Проблемы с получением данных ЦБР сервиса");
        }

        final List<ValuteCursOnDate> courses = response.getGetCursOnDateXmlResult().getValuteData();
        courses.forEach(course -> course.setName(course.getName().trim()));

        List<ValuteCursOnDate> finalCourses = new ArrayList<>();
        courses.forEach(
                course -> {
                    if (Objects.equals(course.getChCode(), "USD") || Objects.equals(course.getChCode(), "EUR")){
                        course.setName(course.getName().trim());
                        finalCourses.add(course);
                    }
                });

        return finalCourses;
    }
}