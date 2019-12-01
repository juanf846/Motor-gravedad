package juanf846.motorGravedad.test;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

import juanf846.motorGravedad.util.Vector2;

class TestClasePunto {

	@org.junit.jupiter.api.Test
	void test() {
		Vector2 p1 = new Vector2(0, 3);
		Vector2 p2 = new Vector2(4, 0);
		Assertions.assertEquals(5, Vector2.distancia(p1, p2));
		
		p1 = new Vector2(-3,4);
		p2 = new Vector2(5,-2);

		Assert.assertEquals(new Vector2(2,2), Vector2.adicion(p1, p2));
		Assert.assertEquals(new Vector2(-1,6), Vector2.adicion(p1, 2));
		Assert.assertEquals(new Vector2(-8,6), Vector2.sustraccion(p1, p2));
		Assert.assertEquals(new Vector2(-5,2), Vector2.sustraccion(p1, 2));
		
		
	}

}
