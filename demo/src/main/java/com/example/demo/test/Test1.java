package com.example.demo.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) throws DocumentException {
        //xmlToMap("<xml><alipay_buyer_login_id><![CDATA[iam***@gmail.*]]></alipay_buyer_login_id><alipay_buyer_user_id><![CDATA[2088412722417473]]></alipay_buyer_user_id><bank_type><![CDATA[ALIPAYHK]]></bank_type><charset><![CDATA[UTF-8]]></charset><coupon_fee><![CDATA[0]]></coupon_fee><fee_type><![CDATA[HKD]]></fee_type><local_fee_type><![CDATA[HKD]]></local_fee_type><local_total_fee><![CDATA[12750]]></local_total_fee><mch_id><![CDATA[101540000658]]></mch_id><nonce_str><![CDATA[rhbnvWwYtJUX2XlQy4Q33BAgYrKuEO]]></nonce_str><openid><![CDATA[2088412722417473]]></openid><order_fee><![CDATA[12750]]></order_fee><out_trade_no><![CDATA[S-BK040452-1381033]]></out_trade_no><out_transaction_id><![CDATA[2022022022001417471439705107]]></out_transaction_id><pay_result><![CDATA[0]]></pay_result><payment_inst><![CDATA[ALIPAYHK]]></payment_inst><result_code><![CDATA[0]]></result_code><sign><![CDATA[81ADC3034A700EAB72D3E3B52049B5B1]]></sign><sign_type><![CDATA[MD5]]></sign_type><status><![CDATA[0]]></status><time_end><![CDATA[20220220132056]]></time_end><total_fee><![CDATA[12750]]></total_fee><trade_type><![CDATA[pay.alipay.micropay]]></trade_type><transaction_id><![CDATA[101540000658202202202180859877]]></transaction_id><uuid><![CDATA[1cda6aed74d9129d045c04ecd5a460b40]]></uuid><version><![CDATA[2.0]]></version></xml>");
        xmlToMap("<xml><charset><![CDATA[UTF-8]]></charset><err_code><![CDATA[USERPAYING]]></err_code><err_msg><![CDATA[??閬\uEF3D\uE705?\uF358颲\uE6A5\uF16F?\uE70C隞\uE9B6?\uF24E?\uEED5]></err_msg><mch_id><![CDATA[181570002787]]></mch_id><need_query><![CDATA[Y]]></need_query><nonce_str><![CDATA[Fhf6oYsxx1NPP3WTi5gAh7jkoNZjgH]]></nonce_str><result_code><![CDATA[1]]></result_code><sign><![CDATA[DF05FF696CDFD2A5B1AEF199FB0A3813]]></sign><sign_type><![CDATA[MD5]]></sign_type><status><![CDATA[0]]></status><version><![CDATA[2.0]]></version></xml>");
    }
    public static Map xmlToMap(String xml) throws DocumentException  {
        try {
            Map<String, String> maps = new HashMap<String, String>();
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            List<Element> eles = root.elements();
            for (Element e : eles) {
                maps.put(e.getName(), e.getTextTrim());
            }
            return maps;
        } catch (DocumentException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
