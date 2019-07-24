package iunin.tools.IuninTools;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class TaxQrcodeTool {

    /**
     * 
     * @param companyName 公司名称
     * @param taxCode     公司税号
     * @param address     公司地址
     * @param tel         公司电话
     * @param bankName    银行名称
     * @param bankAccount 银行卡号
     * @return
     */
    public static String create(String companyName, String taxCode, String address, String tel, String bankName,
            String bankAccount) {
        StringBuffer info = new StringBuffer();
        info.append(companyName).append("</>");
        info.append(taxCode).append("</>");
        info.append(address).append(tel).append("</>");
        info.append(bankName).append(bankAccount).append("</>");

        return createQrcodeContent(info.toString());
    }

    private static String createQrcodeContent(String info) {

        Crc16 crc16 = new Crc16(Crc16.stdPoly, true);
        int crcVal = 0;
        crcVal = crc16.calculate(info.getBytes(), 0);
//        try {
//            crcVal = crc16.calculate(info.getBytes("GB18030"), 0);
//        } catch (UnsupportedEncodingException e) {
//            try {
//                crcVal = crc16.calculate(info.getBytes("GBK"), 0);
//            } catch (UnsupportedEncodingException e1) {
//                e1.printStackTrace();
//            }
//        }
        String content = info + Integer.toHexString(crcVal);

        System.out.println("content: " + content);
        Encoder encoder = Base64.getEncoder();
        
        String base64Str = "";
        base64Str = new String(encoder.encode(content.getBytes()));
//        try {
//            base64Str = new String(encoder.encode(content.getBytes("GB18030")));
//        } catch (UnsupportedEncodingException e) {
//            try {
//                base64Str = new String(encoder.encode(content.getBytes("GBK")));
//            } catch (UnsupportedEncodingException e1) {
//                // TODO Auto-generated catch block
//                e1.printStackTrace();
//            }
//        }
        
        return "$01" + base64Str + "$";
    }

    public static void main(String[] args) {
//        String create = create("航信测试企业", "􏰏􏰏110000999999341􏱬􏱳􏰏", "北京市海淀区路234号", "010-88888888", "中国银行", "012345543345654567");
//        String create = create("深圳市联云计算机科技有限公司", "􏰏􏰏91440300555442072M􏱬􏱳􏰏", "深圳市南山区西丽大勘工业区二楼6号308", "0755-88357790", "中国银行深圳市分行", "754957928561");
//        String create = create("联云", "123456789012345", "3", "4", "5", "6");
        String create = create("云南联云集团有限责任公司", "91530000MA6N3U1J91􏱬􏱳􏰏", "", "", "", "");

        
        System.out.println(create);
    }
}
