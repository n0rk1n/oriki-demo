
package cn.oriki.cxf.client.stub;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.0-SNAPSHOT
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MobileAddressService", targetNamespace = "http://service.cxf.oriki.cn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MobileAddressService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAddressByMobileNo", targetNamespace = "http://service.cxf.oriki.cn/", className = "cn.oriki.cxf.client.stub.GetAddressByMobileNo")
    @ResponseWrapper(localName = "getAddressByMobileNoResponse", targetNamespace = "http://service.cxf.oriki.cn/", className = "cn.oriki.cxf.client.stub.GetAddressByMobileNoResponse")
    public String getAddressByMobileNo(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

}
