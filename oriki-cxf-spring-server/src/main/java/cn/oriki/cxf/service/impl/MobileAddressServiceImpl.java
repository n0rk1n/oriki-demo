package cn.oriki.cxf.service.impl;

import cn.oriki.cxf.service.MobileAddressService;

public class MobileAddressServiceImpl implements MobileAddressService {

    @Override
    public String getAddressByMobileNo(String mobileNo) {
        //根据手机号码查询服务端自己数据库
        String result = mobileNo + "的归属地是上海";

        return result;
    }
}
