<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template  match="/order" >
		<mobileItem>
			<clientId>
				<xsl:value-of select="//head/client-id/text()"/>
			</clientId>
			<productId>
				<xsl:value-of select="//item/sku/text()"/>
			</productId>
			<dateTime>
				<xsl:value-of select="//head/date/text()"/>
			</dateTime>
			<IdOrdenItem>
				<xsl:value-of select="concat(//head/number/text(),'',//item/number/text())"/>
			</IdOrdenItem>
		</mobileItem>
	</xsl:template>
</xsl:stylesheet>