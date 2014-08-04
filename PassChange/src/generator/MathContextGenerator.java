package generator;

import java.util.Random;

public class MathContextGenerator extends ContextPasswordGenerator {

	@Override
	public String generatePassword() {
		String password="";
		Random random=new Random();
		random.setSeed(System.currentTimeMillis());
		switch(random.nextInt(6)){
		case 0:	//sqrt
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
