package br.com.itau.cre.thecat.routes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoutesTest {

	@Test
	public void routesTest() throws Exception {
		CatsDataRouter catsDataRouter = new CatsDataRouter();
		RestRouter restRouter = new RestRouter();
		Assertions.assertThatCode(() -> catsDataRouter.configure()).doesNotThrowAnyException();
		Assertions.assertThatCode(() -> restRouter.configure()).doesNotThrowAnyException();
	}

}
