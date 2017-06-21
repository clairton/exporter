package br.eti.clairton.exporter;

import java.util.Collection;
import java.util.Locale;

import br.eti.clairton.exporter.Report;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;

public class BasicReport extends Report<FastReportBuilder> {

	public BasicReport() {
		super(new FastReportBuilder());
	}

	@Override
	protected void header(final FastReportBuilder builder) throws Exception  {
		builder.setTitle("Test Report");
		builder.setReportLocale(new Locale("pt", "BR"));
	}

	@Override
	protected void footer(final FastReportBuilder builder)  throws Exception {
	}

	@Override
	protected <W>void content(final FastReportBuilder builder, final Collection<W> collection) throws Exception {
		builder.addColumn("name", "name", String.class, 30);
		builder.setUseFullPageWidth(true);
	}
}
