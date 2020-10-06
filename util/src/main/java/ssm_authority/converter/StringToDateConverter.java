package ssm_authority.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * String转换为Date
 * @param null
 * @return
 * @date 2020/7/3 12:23
 */
public class StringToDateConverter implements Converter<String, Date> { //String:接受的类型 Date:目标类型
    @Override
    public Date convert(String s) {

        if(s == null || s.isEmpty()){
            throw new NullPointerException("输入日期为空");
        }
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date parse = simpleDateFormat.parse(s);
            return parse;
        }catch (Exception e){
            throw new RuntimeException("StringToDate失败");
        }
    }
}
