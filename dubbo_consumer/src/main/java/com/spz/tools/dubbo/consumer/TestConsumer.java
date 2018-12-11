/**
 * @文件名称： TestConsumer.java
 * @文件路径： com.test
 * @功能描述： TODO
 * @作者： wangguoliang
 * @创建时间：2017年4月19日 下午2:10:25
 */
package com.spz.tools.dubbo.consumer;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xwtec.module.common.pojo.PublicParam;
import com.xwtec.module.common.pojo.ResultBean;
import com.xwtec.module.saleCard.api.ISaleCardService;
import com.xwtec.module.saleCard.pojo.CardInfo;
import com.xwtec.module.saleCard.pojo.SaleCard;
import com.xwtec.module.saleCard.pojo.SaleCardR;

/**
 * @创建人： wangguoliang
 * @创建时间： 2017年4月19日 下午2:10:25
 */

public class TestConsumer {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        ISaleCardService saleCardService=(ISaleCardService) context.getBean("saleCardService");
        SaleCard card=new SaleCard();
        card.setOrderId("st2018051416053312350");
        card.setStoreId("16053117777");
        card.setPosId("65097777");
        card.setUserId("5000021805083446031");
        card.setAccType("1001");
        CardInfo cardInfo=new CardInfo();
        cardInfo.setLcard_number("1");
        cardInfo.setLcard_balance("1800");
        cardInfo.setLcard_no_begin("5100010000000063850");
        cardInfo.setLcard_no_end("5100010000000063850");
        List<CardInfo> list=new ArrayList<CardInfo>();
        list.add(cardInfo);
        card.setCardList(list);
        card.setTotalBatNum("1");
        PublicParam param=new PublicParam("ectonpay.trade.salecard","20180419160533");
        param.setApp_id("2018030300007148");
        param.setFormat("JSON");
        param.setCharset("utf-8");
        param.setSign_type("MD5");
        param.setVerson("1.0");
        param.setNotify_url("http://192.168.101.58:8180/etonmall_h5/cardManager/saleCardNotify.do");
        //service.qrySaleCard("st2018041916053312350", param);
        ResultBean<SaleCardR> saleCard = saleCardService.saleCard(card, param);
        System.err.println(saleCard.getCode());
        System.err.println(saleCard.getMsg());
        System.err.println(saleCard.getSub_code());
        System.err.println(saleCard.getSub_msg());
        System.err.println(saleCard.getT());
        System.out.println("----------------");
        
        
        /*IChargeService service=(IChargeService) context.getBean("chargeService");
        ChargeCard card=new ChargeCard();
        card.setOrderId("cz20180419160533123459");
        card.setUserId("5000021803090000016");
        card.setStoreId("16053117777");
        card.setPosId("65097777");
        card.setAccType("1001");
        card.setTotalBatNum("1");
        CardInfo cardInfo=new CardInfo();
        cardInfo.setLcard_balance("1000");
        cardInfo.setLcard_no_begin("5100010000000063819");
        cardInfo.setLcard_no_end("5100010000000063819");
        cardInfo.setLcard_number("1");
        List<CardInfo> list=new ArrayList<CardInfo>();
        list.add(cardInfo);
        card.setCardList(list);
        //service.chargeCard(card);
        //service.qryChargeRes("cz20180419160533123459");
        CardToCardTransferParam param=new CardToCardTransferParam();
        param.setOutTradeNo("zz20180419160533123458");
        param.setUserId("5000021803090000016");
        param.setStoreId("16053117777");
        param.setPosId("65097777");
        param.setAccType("1001");
        param.setCardBalance("1000");
        param.setCardNoOut("5100010000000063819");
        param.setCardNoIn("5100010000000063827");
        param.setPin("b562b7fc31749d3cb3bdb9a9f5acd0361f9bf7f9d34e74a5edce56b934aa0ab29892b46e5e80d74f2eb14f4a98a619b27a45766b6ce692934801cdb6ead480d758b01a986b520a45ed8940ffe8785104119638a59cd9c15b85299b6c7dd5d188e7d1d9b6ab22e1392095b7f05047151c2162e27814b3b1002b7c0a5cdee742d49a0245916c80420ae64a3aa3f477be8a3cdc9b13efe7b35e809a090a25cac6ae547ed00ed308080b63b77272f8fc94d2aa2813b9859184ff7cf62f29bbdd948942ef973e7e9356acb3988fb0fb4fda86b666baa7add400b9f0b68af22170f0310aff86747f3c2fd3ca94ee466e709d7deaada3036124d9fc8f446b11aa4eaa7bLzdPYm1zd3guW2MtWT1QZXxpMFxeOFp0a31fO25YKzRTTGBISSdRJipNRDI6Vl0sV2xkPFRFJXVoJD55QEdKKSJDIW9SI3BxZmp");
        service.cardToCardTransfer(param);*/
        //service.queryCardToCardTransferResult("zz20180419160533123457");
        
        
        /*IIdentityAuthService service=(IIdentityAuthService) context.getBean("dentityAuthService");
        PublicParam param=new PublicParam("ectonpay.user.realname","20180427104333");
        param.setApp_id("2018030300007148");
        param.setFormat("JSON");
        param.setCharset("utf-8");
        param.setSign_type("MD5");
        param.setVerson("1.0");
        RealNameParam real=new RealNameParam();
        real.setUserId("5000021803090000016");
        real.setAddress("山东省济南市");
        real.setMobilephone("15275192067");
        real.setCustomer("王国梁");
        real.setCerttype("00");
        real.setCertnum("370827199407163513");
        real.setPin("b8907b8fbd16942409683faec2772017b67cb9def3b4f30903ca384c852b4c265c577721a3db89b7784497777f80e08ea2534096ae07e715335773db8194d8bab8e2d1a13154885281997e67c69b22b82cdbcd522df08b3213ad3814cd9824aea45996a66b49ee3e3f8372afd540ca524a9847c73882918d941e6192e27ce0ffL1NwRm11SU9bZGxIYylzNSozMWlCbiRDdnt3IWVAPX10XVYrPmYncWItLn45R2p6V1BgOFgoTVlRN3x5RSVURFxhbyxKMiY8S047VTZnTHheMGg6UlprX3IiPyNBNA");
        service.realName(real, param);*/
        
        /*IChargeService service=(IChargeService) context.getBean("chargeService");
        PublicParam param=new PublicParam("ectonpay.query.transfer.auth","20180427104333");
        param.setApp_id("2018030300007148");
        param.setFormat("JSON");
        param.setCharset("utf-8");
        param.setSign_type("MD5");
        param.setVerson("1.0");
        
        service.queryTransferAuth("1035", param);*/
    }
    
}
