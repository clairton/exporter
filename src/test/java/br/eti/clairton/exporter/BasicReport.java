package br.eti.clairton.exporter;

import java.util.Locale;

import br.eti.clairton.exporter.Report;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;

public class BasicReport extends Report<FastReportBuilder> {

	public BasicReport() {
		super(new FastReportBuilder());
	}

	@Override
	void header(final FastReportBuilder builder) throws Exception  {
		builder.setTitle("November \"2006\" sales report");
		builder.setReportLocale(new Locale("pt", "BR"));
	}

	@Override
	void footer(final FastReportBuilder builder)  throws Exception {
	}

	@Override
	void content(final FastReportBuilder builder) throws Exception {
		builder.addColumn("name", "name", String.class, 30);
		builder.setUseFullPageWidth(true);
	}
}
