package cadenaDeMando;

import java.io.IOException;

import cadenaDeMando.Handler;
import com.google.gson.stream.JsonReader;

public class Posologies extends Handler {
    private static final String POSOLOGIES_TAGNAME = "posologies";
    private static final String DESCRIPTION_FIELD_TAGNAME = "description";

    public Posologies(Handler hdlr) {
        super(hdlr);
    }


    public StringBuffer readName(JsonReader reader, String name) throws IOException {
        if (name.equals(POSOLOGIES_TAGNAME)) {
            return super.readContent(reader, name);
        } else {
            if (next == null) {
                reader.skipValue();
                System.err.println("Category " + name + " not processed.");
                return new StringBuffer("");

            } else {
                return super.readName(reader, name);
            }
        }
    }

    public String readEntry(JsonReader reader) throws IOException {
        String posName = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(DESCRIPTION_FIELD_TAGNAME)) {
                posName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return posName;
    }
}
