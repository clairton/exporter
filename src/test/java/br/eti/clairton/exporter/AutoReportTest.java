package br.eti.clairton.exporter;

import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class AutoReportTest {

	@Test
	public void test() throws Exception {
		final Service service = new Service(new AutoReport());
		final String path = "target/report" + variable() + ".csv";
		final Collection<Person> collection = datasource();
		assertFalse(new File(path).exists());
		service.export(collection, new HashMap<String, Object>(), path);
		assertTrue(new File(path).exists());
		final Path file = new File(path).toPath();
		List<String> lines = readAllLines(file, Charset.forName("UTF-8"));
		assertFalse(lines.isEmpty());
		assertEquals(2, lines.size());
	}

	private String variable() {
		return new Date().getTime() + "";
	}

	private List<Person> datasource() {
		return asList(new Person("João", new Date()));
	}
}
