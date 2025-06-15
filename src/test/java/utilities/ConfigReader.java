package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static FileInputStream fis;
    static Properties prop=new Properties();
static {
    {
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            prop.load(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    public static String readURL() throws IOException {

        //return pr.getProperty("appURL");
        return prop.getProperty("appURL");
    }

    public static String readEnv() throws IOException {

        return prop.getProperty("execution_env");
    }
}
