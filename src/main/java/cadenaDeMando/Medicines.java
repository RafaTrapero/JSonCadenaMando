package cadenaDeMando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class Medicines extends Handler{

    private static final String MEDICINES_TAGNAME= "medicines";
    private static final String NAME_FIELD_TAGNAME = "name";

    public Medicines (Handler hldr){
        super(hldr);
    }

    public StringBuffer readName(JsonReader reader,String name) throws  IOException {
        if(name.equals(MEDICINES_TAGNAME)){
            return super.readContent(reader,name);
        }else{
            if(next==null){
                reader.skipValue();
                System.err.println("Category: '"+name+"' not processed.");
                return new StringBuffer("");
            }else{
                return super.readName(reader,name);
            }
        }
    }

    @Override
    public String readEntry(JsonReader reader) throws IOException {
        String medName = "";
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                medName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }

        return medName;
    }




}
