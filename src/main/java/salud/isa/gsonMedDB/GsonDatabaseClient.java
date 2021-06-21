package salud.isa.gsonMedDB;

import java.io.FileNotFoundException;
import java.io.IOException;

import cadenaDeMando.*;

public class GsonDatabaseClient {

	public static void main(String[] args) {
		try{
			Medicines med=new Medicines(null);
			ActiveIngredients actv = new ActiveIngredients(med);
			Physiotherapies phys=new Physiotherapies(actv);
			Inhalers inh= new Inhalers(phys);
			Posologies poso=new Posologies(inh);
			MedicinePresentations medpre=new MedicinePresentations(poso);
			RescueMedicinePresentations rmp=new RescueMedicinePresentations(medpre);
			UserManualPhysioSteps usps=new UserManualPhysioSteps(rmp);
			UserManualSteps ums =new UserManualSteps(usps);

			DatabaseJSonReader dbjp = new DatabaseJSonReader(ums);

			try {
				System.out.println(dbjp.parse("./src/main/resources/datos.json"));
			} finally {
			}
		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

	}

}
