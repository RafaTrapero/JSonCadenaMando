package cadenaDeMando;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;


import java.io.IOException;

import static uitls.Utils.isMulti;
import static uitls.Utils.isMulti;

public class RescueMedicinePresentations extends Handler {

    private static final String RESCUEMEDPRES_TAGNAME="rescueMedicinePresentations";
    private static final String MEDREF_FIELD_TAGNAME="medicineRef";
    private static final String ACTINGREF_FIELD_TAGNAME = "activeIngRef";
    private static final String INHREF_FIELD_TAGNAME = "inhalerRef";
    private static final String DOSE_FIELD_TAGNAME = "dose";

    private static final String SEPARATOR = ";";

    public RescueMedicinePresentations(Handler hdlr){
        super(hdlr);
    }

    public StringBuffer readName(JsonReader reader,String name) throws  IOException{
        if(name.equals(RESCUEMEDPRES_TAGNAME)){
            return super.readContent(reader,name);
        }else{
            if(next==null){
                reader.skipValue();
                System.err.println("Category " +name+ "not processed.");
                return new StringBuffer("");
            }else{
                return super.readName(reader,name);

            }
        }
    }


    public String readEntry(JsonReader reader) throws IOException {
        String medRef="";
        String actIngRef="";
        String inhRef="";
        String dose="";
        while(reader.hasNext()){
            String name=reader.nextName();
            if(name.equals(MEDREF_FIELD_TAGNAME)){
                medRef=reader.nextString();
            }else if(name.equals(ACTINGREF_FIELD_TAGNAME)){
                actIngRef=reader.nextString();
            }else if(name.equals(INHREF_FIELD_TAGNAME)){
                if(!isMulti(reader)){
                    inhRef=reader.nextString();
                }else{
                    reader.beginArray();
                    while (reader.hasNext()){
                        inhRef=inhRef+reader.nextString()+", ";
                    }
                    reader.endArray();
                    //para quitar la coma y el espacio del final
                    inhRef=inhRef.substring(0,inhRef.length()-2);
                }

            }else if(name.equals(DOSE_FIELD_TAGNAME)){
                if(!isMulti(reader)){
                    dose=reader.nextString();
                }else{
                    reader.beginArray();
                    while (reader.hasNext()){
                        dose=dose+reader.nextString()+", ";
                    }
                    reader.endArray();
                    //para quitar la coma y el espacio del final
                    dose=dose.substring(0,dose.length()-2);
                }
            }else{
                reader.skipValue();
            }
        }
        return medRef + SEPARATOR+ actIngRef + SEPARATOR + inhRef+ SEPARATOR+ dose;
    }


}
