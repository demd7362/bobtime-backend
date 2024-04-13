package com.bobtime.common.strategy;


import com.bobtime.common.exception.NotSupportedFieldNameException;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.util.StringUtils;

public class CamelToSnakeUpperStrategy extends PhysicalNamingStrategyStandardImpl {
    @Override
    public Identifier toPhysicalColumnName(Identifier logicalName, JdbcEnvironment jdbcEnvironment) {
        String newLogicalName = convertToSnakeCase(logicalName.getText());
        return Identifier.toIdentifier(newLogicalName);
    }

    private String convertToSnakeCase(String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }
        name = name.replaceAll("[_-]","");

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);

            if (Character.isLowerCase(c)) {
                builder.append(Character.toUpperCase(c));
            } else if (Character.isUpperCase(c)) {
                if (i > 0) {
                    builder.append("_");
                }
                builder.append(c);
            } else {
                throw new NotSupportedFieldNameException(c + " is invalid in the field name '" + name + "'");
            }
        }
        return builder.toString();
    }

}
