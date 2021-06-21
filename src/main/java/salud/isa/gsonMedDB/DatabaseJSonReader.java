package salud.isa.gsonMedDB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import cadenaDeMando.Handler;
import cadenaDeMando.Medicines;
import com.google.gson.stream.JsonReader;

/**
 * Created by jmalvarez on 11/5/16.
 * http://developer.android.com/intl/es/training/basics/network-ops/xml.html
 */
public class DatabaseJSonReader {
    Handler hdlr;


	public DatabaseJSonReader(Handler hd) {
        hdlr=hd;
	}


	public String parse(String jsonFileName) throws IOException {

		InputStream usersIS = new FileInputStream(new File(jsonFileName));
		JsonReader reader = new JsonReader(new InputStreamReader(usersIS, "UTF-8"));

		reader.beginObject();
		StringBuffer readData = new StringBuffer();

		while (reader.hasNext()) {
			String name = reader.nextName();
			readData.append(name.toUpperCase()).append("\n").append(readName(reader,name)).append("\n");
		}

		reader.endObject();
		reader.close();
		usersIS.close();

		return new String(readData);
	}

	public StringBuffer readName(JsonReader reader, String name) throws  IOException{
        return hdlr.readName(reader,name);
    }




}
