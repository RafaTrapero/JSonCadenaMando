package cadenaDeMando;


import com.google.gson.stream.JsonReader;

import java.io.IOException;

public abstract class Handler {

    protected Handler next;

    public Handler(Handler hdlr) {
        next= hdlr;
    }

    // Lee el contenido de la categoría (esta se indica con su nombre)
    public StringBuffer readContent(JsonReader reader, String name) throws IOException{
        StringBuffer data= new StringBuffer();
        reader.beginArray();
        while(reader.hasNext()){
            reader.beginObject();
            data.append(readEntry(reader)).append("\n");
            reader.endObject();
        }
        data.append("\n");
        reader.endArray();
        return data;
    }

    public  StringBuffer readName(JsonReader reader, String name) throws IOException{
        return next.readName(reader,name);
    }
    public abstract String readEntry(JsonReader reader) throws IOException;
}
