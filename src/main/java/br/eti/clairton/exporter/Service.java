package br.eti.clairton.exporter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JasperPrint;

public class Service {
	private Map<Pattern, Writer> options = new HashMap<Pattern, Writer>() {
		private static final long serialVersionUID = 1L;
		{
			put(Pattern.compile(".*\\.pdf"), Writer.PDF);
			put(Pattern.compile(".*\\.xls"), Writer.XLS);
			put(Pattern.compile(".*\\.html"), Writer.HTML);
		}
	};
	private final Report<?> report;

	public Service(final Report<?> report) {
		super();
		this.report = report;
	}

	public <T> void export(final Collection<T> collection, final String path) throws Exception {
		for (final Pattern pattern : options.keySet()) {
			if (pattern.matcher(path).find()) {
				final JasperPrint jp = report.build(collection);
				options.get(pattern).run(jp, path);
				return;
			}
		}
		throw new IllegalArgumentException(path
				+ " no pattern .pdf, .csv or .html");
	}
}
