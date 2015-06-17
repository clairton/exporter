package br.eti.clairton.exporter;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE;
import static net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS;
import static net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND;

import java.io.File;
import java.io.FileOutputStream;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public interface Writer {
	public static Writer PDF = new Writer() {

		public void run(final JasperPrint jp, final String path) throws Exception {

			final JRPdfExporter exporter = new JRPdfExporter();

			final File outputFile = new File(path);
			final File parentFile = outputFile.getParentFile();
			if (parentFile != null) {
				parentFile.mkdirs();
			}
			final FileOutputStream fos = new FileOutputStream(outputFile);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);

			exporter.exportReport();
		}
	};
	public static Writer XLS = new Writer() {

		public void run(final JasperPrint jp, final String path) throws Exception {

			final JRXlsExporter exporter = new JRXlsExporter();

			final File outputFile = new File(path);
			final File parentFile = outputFile.getParentFile();
			if (parentFile != null) {
				parentFile.mkdirs();
			}
			final FileOutputStream fos = new FileOutputStream(outputFile);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.setParameter(IS_DETECT_CELL_TYPE, TRUE);
			exporter.setParameter(IS_WHITE_PAGE_BACKGROUND, FALSE);
			exporter.setParameter(IS_IGNORE_GRAPHICS, FALSE);

			exporter.exportReport();

		}
	};
	public static Writer HTML = new Writer() {

		public void run(final JasperPrint jp, final String path) throws Exception {

			final JRHtmlExporter exporter = new JRHtmlExporter();

			final File outputFile = new File(path);
			final File parentFile = outputFile.getParentFile();
			if (parentFile != null) {
				parentFile.mkdirs();
			}
			final FileOutputStream fos = new FileOutputStream(outputFile);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.exportReport();

		}
	};
	public static Writer CSV = new Writer() {

		public void run(final JasperPrint jp, final String path) throws Exception {

			final JRCsvExporter exporter = new JRCsvExporter();

			final File outputFile = new File(path);
			final File parentFile = outputFile.getParentFile();
			if (parentFile != null) {
				parentFile.mkdirs();
			}
			final FileOutputStream fos = new FileOutputStream(outputFile);

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fos);
			exporter.exportReport();

		}
	};

	void run(final JasperPrint jp, final String path) throws Exception;

}
