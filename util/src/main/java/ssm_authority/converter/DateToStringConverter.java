package ssm_authority.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToStringConverter implements Converter<Date,String> {
    @Override
    public String convert(Date date) {
        SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sDate.format(date);
        return format;
    }
}
