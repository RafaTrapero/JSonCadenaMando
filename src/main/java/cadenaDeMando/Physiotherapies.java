package cadenaDeMando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class Physiotherapies extends Handler{

    private static final String PHYSIO_TAGNAME= "physiotherapies";
    private static final String NAME_FIELD_TAGNAME = "name";
    private static final String IMAGE_FIELD_TAGNAME = "image";
    private static final String SEPARATOR = ";";

    public Physiotherapies(Handler hdlr){
        super(hdlr);
    }

    public StringBuffer readName(JsonReader reader,String name) throws IOException {
        if(name.equals(PHYSIO_TAGNAME)){
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
        String actvIngName = "";
        String imageName="";
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                actvIngName = reader.nextString();
            }else if(name.equals(IMAGE_FIELD_TAGNAME)){
                imageName=reader.nextString();
            }else {
                reader.skipValue();
            }
        }

        return actvIngName + SEPARATOR + imageName;
    }


}
