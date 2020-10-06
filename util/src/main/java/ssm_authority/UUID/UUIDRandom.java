package ssm_authority.UUID;

import java.util.UUID;

public class UUIDRandom {

    public static String random(){
        String s = UUID.randomUUID().toString().toUpperCase();
        return s.replaceAll("-","");
    }
}
