package br.eti.clairton.exporter;

import static java.nio.file.Files.readAllLines;
import static java.util.Arrays.asList;
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

import br.eti.clairton.exporter.Service;

public class ServiceTest {

	private final List<String> extensions = asList(new String[] { ".xls", ".pdf", ".html" });

	@Test
	public void testExport() throws Exception {
		final Service service = new Service(new BasicReport());
		for (final String extension : extensions) {
			final String path = "target/report" + variable() + extension;
			final Collection<Person> collection = datasource();
			assertFalse(new File(path).exists());
			service.export(collection, new HashMap<String, Object>(), path);
			assertTrue(new File(path).exists());
			if (".html".equals(extension)) {
				final Path file = new File(path).toPath();
				assertFalse(readAllLines(file, Charset.forName("UTF-8")).isEmpty());
			}
		}
	}	

	private String variable() {
		return new Date().getTime() + "";
	}

	
	private List<Person> datasource(){
		return asList(new Person("João", new Date()));
	}
}
