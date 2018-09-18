<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:tns="http://order.nure.ua/entity"
	exclude-result-prefixes="tns"
	>
	<xsl:output method="html" include-content-type="yes" indent="yes"/>
	<xsl:template match="/">
		<xsl:text disable-output-escaping="yes">&lt;!DOCTYPE html&gt;</xsl:text>
		<html>
		<head>
		</head>
		<body>
			<h2>Some Text</h2>
			<xsl:apply-templates />
		</body>
		</html>
	</xsl:template>
	<xsl:template match="tns:book">
		
		<xsl:apply-templates select="tns:title" />
		<xsl:variable name="price"><xsl:value-of select="tns:price" /></xsl:variable>
		<xsl:choose>
			<xsl:when test="$price=0.0">
				<hr color="red"/>
			</xsl:when>
			<xsl:otherwise>
				<hr />
			</xsl:otherwise>
		</xsl:choose> 
		Authors:
		<ul>
		<xsl:for-each select="tns:author">
			<li><xsl:value-of select="." /></li>
		</xsl:for-each>
		</ul>
	</xsl:template>
	<xsl:template match="tns:title">
		Title:
		<span style="#4a4a4a; font-weight: bold">
			<xsl:value-of select="." />
		</span>
	</xsl:template>
	<xsl:template match="tns:author">
	</xsl:template>

</xsl:stylesheet>