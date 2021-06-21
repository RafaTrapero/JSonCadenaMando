package cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualPhysioSteps extends Handler {
    private static final String USERMANUALPHY_TAGNAME = "userManualPhysioSteps";
    private static final String STITLE_FIELD_TAGNAME = "stepTitle";
    private static final String SIMAGE_FIELD_TAGNAME = "stepImage";
    private static final String STEXT_FIELD_TAGNAME = "stepText";
    private static final String PHYREF_FIELD_TAGNAME = "physioRef";
    private static final String SEPARATOR = ";";

    public UserManualPhysioSteps(Handler hdlr) {
        super(hdlr);
    }

    public StringBuffer readName(JsonReader reader, String name) throws IOException {
        if (name.equals(USERMANUALPHY_TAGNAME)) {
            return super.readContent(reader, name);
        }

        else {
            if (next != null) {
                return super.readName(reader, name);
            } else {
                reader.skipValue();
                System.err.println("Category " + name + " not processed.");
                return new StringBuffer("");
            }
        }
    }

    public String readEntry(JsonReader reader) throws IOException {
        String stepTitle = "";
        String stepImage = "";
        String stepText = "";
        String physioRef = "";
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(STITLE_FIELD_TAGNAME)) {
                stepTitle = reader.nextString();
            } else if (name.equals(SIMAGE_FIELD_TAGNAME)) {
                stepImage = reader.nextString();
            } else if (name.equals(STEXT_FIELD_TAGNAME)) {
                stepText = reader.nextString();
            } else if (name.equals(PHYREF_FIELD_TAGNAME)) {
                physioRef = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return stepTitle + SEPARATOR + stepImage + SEPARATOR + stepText + SEPARATOR + physioRef;
    }
}
