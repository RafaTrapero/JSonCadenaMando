package uitls;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;

public class Utils {

    public static boolean isMulti(JsonReader reader) throws IOException {
        Boolean isArray=false;
        if(reader.peek()== JsonToken.BEGIN_ARRAY){
            isArray=true;
        }
        return isArray;
    }
}
