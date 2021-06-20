package game;

import java.util.Random;

public class Brick{
	
	void destroyed(Grass location,boolean key_has_obtained) {
		int xas=new Random().nextInt();
		if(xas%10<7) {
			int x=4;
			if(key_has_obtained) x=3;
			int a=new Random().nextInt(x);
			System.out.println("Result: "+a);
			location.assign_item(a);
		}
		location.setBrick(null);
	}
	
}
