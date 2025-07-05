package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties props;

    public static Properties initProps(){
        props = new Properties();
        try {
            FileInputStream file =new FileInputStream("src/test/resources/config/config.properties");
            props.load(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        return props;
    }

    public static String getProperty(String key){
        if (props == null){
            initProps();
        }
        return props.getProperty(key);
    }


}
