package com.mycompany;

import com.mycompany.webservice.DemoWebService;
import com.mycompany.webservice.DemoWebService_Service;
import com.mycompany.webservice.MyException_Exception;
import javax.ejb.Stateless;
import javax.xml.ws.WebServiceRef;
import org.apache.log4j.Logger;

/**
 *
 * @author Юрий Кузнецов <kuznetsov_yura@mail.ru>
 */
@Stateless
public class ClientService {

    private static final Logger log = Logger.getLogger(ClientService.class);

    @WebServiceRef
    private DemoWebService_Service webService;

    public ClientService() {
    }

    public Boolean splitRevertBusiness(String str) throws Exception {
        DemoWebService demoWebService;
        Boolean result = null;

        try {
            demoWebService = webService.getDemoWebServicePort();
            result = demoWebService.splitRevert(str);
        } catch (MyException_Exception e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("Ошибка работы сервиса");
        }
        return result;
    }

}
