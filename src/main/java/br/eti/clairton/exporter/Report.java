package br.eti.clairton.exporter;

import static ar.com.fdvs.dj.core.DynamicJasperHelper.generateJasperReport;
import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

	public <W> JasperPrint build(final Collection<W> collection) throws Exception {
		final DynamicReport dynamic = build(builder);
		final JRDataSource ds = new JRBeanCollectionDataSource(collection);
		final Map<String, Object> parameters = new HashMap<String, Object>();
		final JasperReport jr = generateJasperReport(dynamic, layout(), parameters);
		return fillReport(jr, parameters, ds);
	}

	public LayoutManager layout() {
		return new ClassicLayoutManager();
	}

	public DynamicReport build(T builder) throws Exception{
		header(builder);
		content(builder);
		footer(builder);
		return builder.build();
	}

	abstract void header(T builder) throws Exception ;

	abstract void footer(T builder) throws Exception ;

	abstract void content(T builder) throws Exception ;
}