package utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class TestDataUtil {
    public static JSONObject getLoginData() {

        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/resources/validLoginCredentials.json");
            Object obj = parser.parse(reader);

            return (JSONObject) obj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
}


}
