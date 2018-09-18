<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:tns="http://order.nure.ua/entity"
	exclude-result-prefixes="tns"
	>

	<xsl:output method="html" encoding="UTF-8" include-content-type="yes" indent="yes"/>
	<xsl:template match="/">
		<xsl:variable name="bbb" select="/descendant::tns:book"/>
		<html>
		<head></head>
		<body>
			<h2>Каталог</h2>
			<xsl:for-each select="$bbb">
				<p><xsl:value-of select="tns:title" /></p>
				<p><xsl:value-of select="tns:price" /></p>
				<hr/>
			</xsl:for-each>
			<!-- <xsl:apply-templates /> -->
		</body>
		</html>
	</xsl:template>
	<!-- <xsl:template match="book">
		<xsl:variable name="bbb" select="."/>
		<xsl:for-each select="$bbb">
			<p><xsl:value-of select="title" /></p>
			<p><xsl:value-of select="price" /></p>
			<hr/>
		</xsl:for-each>
	</xsl:template> -->
	
	<xsl:attribute-set name="id">
		<xsl:attribute name="id">some</xsl:attribute>
		<xsl:attribute name="name">someName</xsl:attribute>
	</xsl:attribute-set>
</xsl:stylesheet>