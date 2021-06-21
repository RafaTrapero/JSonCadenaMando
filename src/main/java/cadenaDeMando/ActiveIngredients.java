package cadenaDeMando;

import com.google.gson.stream.JsonReader;

import java.io.IOException;

public class ActiveIngredients extends Handler{

    private static final String ACTIVEINGREDIENTS_TAGNAME= "activeIngredients";
    private static final String NAME_FIELD_TAGNAME = "name";

    public ActiveIngredients (Handler hdlr){
        super(hdlr);
    }

    public StringBuffer readName(JsonReader reader,String name) throws  IOException {
        if(name.equals(ACTIVEINGREDIENTS_TAGNAME)){
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
        String actvName = "";
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals(NAME_FIELD_TAGNAME)) {
                actvName = reader.nextString();
            } else {
                reader.skipValue();
            }
        }

        return actvName;
    }


}
