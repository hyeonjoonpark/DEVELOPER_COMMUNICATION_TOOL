package org.hyunjooon.communication_devtools.global.config;

import com.p6spy.engine.common.ConnectionInformation;
import com.p6spy.engine.event.JdbcEventListener;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.SQLException;

@Component
public class P6SpyFormatter extends JdbcEventListener implements MessageFormattingStrategy {

    @Override
    public void onAfterGetConnection(ConnectionInformation connectionInformation, SQLException e) {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(getClass().getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(category).append(" ").append(elapsed).append("ms").append("\n");
        if (StringUtils.hasText(sql)) {
            sb.append(highlight(format(sql)));
        }
        return sb.toString();
    }

    private String format(String sql) {
        if (isDDL(sql)) {
            return FormatStyle.DDL.getFormatter().format(sql);
        }
        if (!isBasic(sql)) {
            return sql;
        }

        if (sql.contains("*/")) {
            int endIndex = sql.indexOf("*/") + 2;

            String jpqlPart = sql.substring(0, endIndex);
            String sqlPart = sql.substring(endIndex);
            String jpqlFormat = FormatStyle.BASIC.getFormatter().format(jpqlPart);
            String sqlFormat = FormatStyle.BASIC.getFormatter().format(sqlPart);

            StringBuilder sb = new StringBuilder();
            sb.append("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<JPQL")
                    .append(jpqlFormat)
                    .append("\n------------------------------------")
                    .append(sqlFormat)
                    .append("\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<SQL\n");

            return sb.toString();
        }
        return FormatStyle.BASIC.getFormatter().format(sql);
    }

    private String highlight(String sql) {
        return FormatStyle.HIGHLIGHT.getFormatter().format(sql);
    }

    private boolean isDDL(String sql) {
        return sql.startsWith("create") || sql.startsWith("alter") || sql.startsWith("comment");
    }

    private boolean isBasic(String sql) {
        return sql.startsWith("select") || sql.startsWith("insert") || sql.startsWith("update") || sql.startsWith("delete")
                || sql.startsWith("/* select") || sql.startsWith("/* insert") || sql.startsWith("/* update") || sql.startsWith("/* delete");
    }

}