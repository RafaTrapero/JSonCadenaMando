package cadenaDeMando;

import java.io.IOException;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import static uitls.Utils.isMulti;

public class MedicinePresentations extends Handler {
    private static final String MEDPRESENTATION_TAGNAME = "medicinePresentations";
    private static final String MEDREF_FIELD_TAGNAME = "medicineRef";
    private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
    private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
    private static final String DOSE_FIELD_TAGNAME = "dose";
    private static final String POSREF_FIELD_TAGNAME = "posologyRef";
    private static final String SEPARATOR = ";";

    public MedicinePresentations(Handler hdlr) {
        super(hdlr);
    }

    public StringBuffer readName(JsonReader reader, String name) throws IOException {
        if (name.equals(MEDPRESENTATION_TAGNAME)) {
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
        String medRef = "";
        String aiRef = "";
        String inhRef = "";
        String dose = "";
        String posRef = "";
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(MEDREF_FIELD_TAGNAME)) {
                medRef = reader.nextString();
            } else if (name.equals(ACTINGREF_FIELD_TAGNAME)) {
                aiRef = reader.nextString();
            } else if (name.equals(INHREF_FIELD_TAGNAME)) {
                if (!isMulti(reader)) {
                    inhRef = reader.nextString();
                } else if (isMulti(reader)) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        inhRef = inhRef + reader.nextString() + ", ";
                    }
                    reader.endArray();
                    // Para quitar la coma y el espacio del final
                    inhRef = inhRef.substring(0, inhRef.length() - 2);
                } else {
                    inhRef = "ERROR";
                }
            } else if (name.equals(DOSE_FIELD_TAGNAME)) {
                if (!isMulti(reader)) {
                    dose = reader.nextString();
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        dose = dose + "(" + reader.nextString() + "), ";
                    }
                    reader.endArray();
                    // Para quitar la coma y el espacio del final.
                    dose = dose.substring(0, dose.length() - 2);
                }
            } else if (name.equals(POSREF_FIELD_TAGNAME)) {
                if (!isMulti(reader)) {
                    posRef = reader.nextString();
                } else {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        posRef = posRef + reader.nextString() + ", ";
                    }
                    reader.endArray();
                    // Para quitar la coma y el espacio del final
                    posRef = posRef.substring(0, posRef.length() - 2);
                }
            } else {
                reader.skipValue();
            }
        }
        return medRef + SEPARATOR + aiRef + SEPARATOR + inhRef + SEPARATOR + dose + SEPARATOR
                + posRef;
    }


}
