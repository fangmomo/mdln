package mysb.mdln.utils;

import java.util.UUID;

public class StringUtils {

	/*
	 * 获取id
	 */
	 public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        return uuid.toString().replace("-","");
    }
}
