# exporter[![Build Status](https://drone.io/github.com/clairton/exporter/status.png)](https://drone.io/github.com/clairton/exporter/latest)

Exportar dados através do Jasper Reports para xls, pdf e html.
Exemplo:

```java
Service service = new Service(new AutoReport());
Collection<Person> collection = asList(new Person("João", new Date()));
String path = "/people.pdf";// ou .xls, ou .html
service.export(collection, path);
```
No exemplo acima todas os atributos serão listados, para filtra-los é possível passar um Matcher.
```java
Service service = new Service(new AutoReport(new Matcher<Field>() {
	public boolean accepts(final Field element) {
		return !field.getName().equals("abobora");
	}
}));
```

Necessário adicionar os repositórios maven:

```xml
<repository>
	<id>mvn-repo-releases</id>
	<url>https://raw.github.com/clairton/mvn-repo/releases</url>
</repository>
<repository>
	<id>mvn-repo-snapshot</id>
	<url>https://raw.github.com/clairton/mvn-repo/snapshots</url>
</repository>
```
 Também adicionar as depêndencias:
```xml
<dependency>
    <groupId>br.eti.clairton</groupId>
	<artifactId>exporter</artifactId>
	<version>0.1.0</version>
</dependency>
<!--Libs externas usadas-->

<dependency>
	<groupId>ar.com.fdvs</groupId>
	<artifactId>DynamicJasper</artifactId>
	<version>5.0.0</version>
</dependency>
<dependency>
	<groupId>org.apache.poi</groupId>
	<artifactId>poi</artifactId>
	<version>3.12</version>
</dependency>
<dependency>
	<groupId>com.itextpdf</groupId>
	<artifactId>itextpdf</artifactId>
	<version>5.5.6</version>
</dependency>
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-digester3</artifactId>
	<version>3.2</version>
</dependency>
<dependency>
	<groupId>javax.xml</groupId>
	<artifactId>jaxp-api</artifactId>
	<version>1.4.2</version>
</dependency>
<dependency>
	<groupId>net.vidageek</groupId>
	<artifactId>mirror</artifactId>
	<version>1.6.1</version>
</dependency>
```
