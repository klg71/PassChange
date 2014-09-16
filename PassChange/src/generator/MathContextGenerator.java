package generator;

import java.util.Date;
import java.util.Random;

public class MathContextGenerator extends ContextPasswordGenerator {

	@Override
	public String generatePassword() {
		String password="";
		Random random=new Random(new Date().getTime());
		switch(random.nextInt(6)){
		case 0:	//sqrt
		{
			int rand=random.nextInt(99);
			if(random.nextBoolean()){
				password="sqrt+("+Double.toString(Math.pow(rand,2))+")=="+Integer.toString(rand);
			} else {
				password="sqrt+("+Double.toString(Math.pow(rand,2))+")equals"+Integer.toString(rand);
			}
			if(random.nextBoolean()){
				password+="or"+Long.toString(Math.round(Math.pow(rand,2)/3));
			} else {
				password+="||"+Long.toString(Math.round(Math.pow(rand,2)/3));
			}
		}
		case 1:	//pow
		case 2:	//sin 
		case 3:	//cos
		case 4: //Arithmetisches Mittel
		case 5: //Zahlenfolge
		case 6: //Primfaktoren
		}
		return null;
	}

}
