import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.io.FileInputStream;
//import java.io.InputStream;

public class SoundRecorder
{


    public static void main(String[] args)
    {
        String ACCESS_TOKEN = "nU09ZyGYZZsAAAAAAAARulltXnEK0LlHO2AelttLWIAGcVoJwoWwDfDrpuCn-odu";

        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        long milliseconds = 10000;



        DropBoxConnection connection = new DropBoxConnection();
        JavaSoundRecorder recorder = new JavaSoundRecorder(connection);
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        new Thread(() -> {
        for (int i = 0; i < 3; i++) {
            Date date = new Date();
            String fileName = dateFormat.format(date)+".wav";
            recorder.recordSound(milliseconds, fileName);

                try {
                    Thread.sleep(milliseconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


        }
        }).start();

//        try {
//            Thread.sleep(15000);
//            InputStream in = new FileInputStream(fileName);
//            client.files().uploadBuilder("/" + fileName)
//                    .uploadAndFinish(in);
//        }
//        catch (Exception ex) {
//            ex.printStackTrace();
//        }



    }

}
