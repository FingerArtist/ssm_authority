package ssm_authority;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductUtils {

    /**
     * Date转换为字符串
     * @param date
     * @return java.lang.String
     * @date 2020/7/3 12:04
     */
    public static String Date2String(Date date){
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sDate.format(date);
        return format;
    }

}
