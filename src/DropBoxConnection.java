import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class DropBoxConnection {
    private String ACCESS_TOKEN = "nU09ZyGYZZsAAAAAAAARulltXnEK0LlHO2AelttLWIAGcVoJwoWwDfDrpuCn-odu";
        private DbxClientV2 client;
//
    public DropBoxConnection() {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        this.client = new DbxClientV2(config, ACCESS_TOKEN);
    }

    public void uploadFile (String fileName) {
        // Upload "test.txt" to Dropbox
        try {
            InputStream in = new FileInputStream(fileName);
            client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(in);
            in.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

