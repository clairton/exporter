package br.eti.clairton.exporter;

import static ar.com.fdvs.dj.core.DynamicJasperHelper.generateJasperReport;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.core.layout.LayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;

public abstract class Report<T extends DynamicReportBuilder> {

	private final T builder;

	public Report(final T builder) {
		super();
		this.builder = builder;
	}

	public LayoutManager layout() {
		return new ClassicLayoutManager();
	}

	public <W>JasperPrint build(final Collection<W> collection, final Map<String, Object> parameters) throws Exception{
		header(builder);
		content(builder, collection);
		footer(builder);
		final DynamicReport dynamic = builder.build();
		return build(dynamic, parameters);
	}

	public <W>JasperPrint build(final Collection<W> collection) throws Exception{
		return build(collection, new HashMap<String, Object>());
	}
	
	public JasperPrint build(DynamicReport report, final Map<String, Object> parameters) throws Exception{
		final JasperReport jr = generateJasperReport(report, layout(), parameters);
		return fillReport(jr, parameters);		
	}

	abstract void header(T builder) throws Exception ;

	abstract void footer(T builder) throws Exception ;

	abstract <W>void content(T builder, final Collection<W> collection) throws Exception ;
}