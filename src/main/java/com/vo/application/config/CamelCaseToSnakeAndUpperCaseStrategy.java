package com.vo.application.config;

import java.util.Locale;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CamelCaseToSnakeAndUpperCaseStrategy extends CamelCaseToUnderscoresNamingStrategy{

	@Override
	protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
		if ( isCaseInsensitive( jdbcEnvironment ) ) {
			name = name.toUpperCase( Locale.ROOT );
		}
		return new Identifier( name, quoted );
	}

}
