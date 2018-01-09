package cn.oriki.cxf.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface MobileAddressService {

    @WebMethod
    String getAddressByMobileNo(String mobileNo);
}