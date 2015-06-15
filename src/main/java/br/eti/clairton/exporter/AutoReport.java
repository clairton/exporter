package br.eti.clairton.exporter;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JasperPrint;
import net.vidageek.mirror.dsl.Mirror;
import net.vidageek.mirror.list.dsl.Mapper;
import net.vidageek.mirror.list.dsl.Matcher;
import net.vidageek.mirror.list.dsl.MirrorList;
import net.vidageek.mirror.reflect.dsl.AllReflectionHandler;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ReflectiveReportBuilder;

public class AutoReport extends Report<ReflectiveReportBuilder> {
	private final Mirror mirror = new Mirror();
	private final Mapper<Field, String> mapper = new Mapper<Field, String>() {

		public String map(final Field element) {
			return element.getName();
		}
	};
	private final Matcher<Field> matcher;
	private Collection<?> collection;

	public AutoReport() {
		this(new Matcher<Field>() {

			public boolean accepts(final Field element) {
				return true;
			}
		});
	}

	public AutoReport(final Matcher<Field> matcher) {
		super(null);
		this.matcher = matcher;
	}

	@Override
	public <W> JasperPrint build(final Collection<W> collection)
			throws Exception {
		this.collection = collection;
		return super.build(collection);
	}

	@Override
	public DynamicReport build(final ReflectiveReportBuilder builder)
			throws Exception {
		if (collection.isEmpty()) {
			return super.build(builder);
		} else {
			final String[] columns = columns();
			return new ReflectiveReportBuilder(collection, columns).build();
		}
	}

	public String[] columns() {
		final Iterator<?> iterator = collection.iterator();
		final Class<?> klazz = iterator.next().getClass();
		final AllReflectionHandler<?> handler = mirror.on(klazz).reflectAll();
		final MirrorList<Field> fields = handler.fields();
		final List<String> columns = fields.matching(matcher).mappingTo(mapper);
		return columns.toArray(new String[columns.size()]);
	}

	@Override
	void header(final ReflectiveReportBuilder builder) throws Exception {
	}

	@Override
	void footer(final ReflectiveReportBuilder builder) throws Exception {
	}

	@Override
	void content(final ReflectiveReportBuilder builder) throws Exception {
	}
}
