import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testDropBox {
    private static final String ACCESS_TOKEN = "<ACCESS TOKEN>";

    public static void main(String args[]) throws DbxException {
//     Create Dropbox client
//        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
//        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));//2016/11/16 12:08:43
//}
}
}

