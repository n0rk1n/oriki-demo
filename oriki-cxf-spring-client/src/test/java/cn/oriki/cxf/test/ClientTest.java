package cn.oriki.cxf.test;

import cn.oriki.cxf.client.stub.MobileAddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ClientTest {

    @Autowired
    private MobileAddressService mobileAddressService;

    @Test
    public void test() {
        String address = mobileAddressService.getAddressByMobileNo("18516566511");
        System.out.println(address);
    }

}
