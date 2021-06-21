package cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class UserManualSteps extends Handler {
    private static final String USERMANUAL_TAGNAME = "userManualSteps";
    private static final String STITLE_FIELD_TAGNAME = "stepTitle";
    private static final String SIMAGE_FIELD_TAGNAME = "stepImage";
    private static final String STEXT_FIELD_TAGNAME = "stepText";
    private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
    private static final String SEPARATOR = ";";

    public UserManualSteps(Handler hdlr) {
        super(hdlr);
    }

    public StringBuffer readName(JsonReader reader, String name) throws IOException {
        if (name.equals(USERMANUAL_TAGNAME)) {
            return super.readContent(reader, name);
        }

        else {
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
        String stepTitle = null;
        String stepImage = null;
        String stepText = null;
        String inhRef = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(STITLE_FIELD_TAGNAME)) {
                stepTitle = reader.nextString();
            } else if (name.equals(SIMAGE_FIELD_TAGNAME)) {
                stepImage = reader.nextString();
            } else if (name.equals(STEXT_FIELD_TAGNAME)) {
                stepText = reader.nextString();
            } else if (name.equals(INHREF_FIELD_TAGNAME)) {
                inhRef = reader.nextString();
            } else {
                reader.skipValue();
            }
        }
        return stepTitle + SEPARATOR + stepImage + SEPARATOR + stepText + SEPARATOR + inhRef;
    }
}
