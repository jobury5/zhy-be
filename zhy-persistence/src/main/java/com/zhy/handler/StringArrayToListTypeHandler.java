package com.zhy.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: jobury
 * @Date: 2024/10/8 17:12
 */

public class StringArrayToListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null || parameter.isEmpty()) {
            ps.setString(i, null);
        } else {
            ps.setString(i, parameter.toString());
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if (result == null) {
            return null;
        }
        return Arrays.asList(result.replaceAll("\\[|\\]", "").split(",\\s*"));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if (result == null) {
            return null;
        }
        return Arrays.asList(result.replaceAll("\\[|\\]", "").split(",\\s*"));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if (result == null) {
            return null;
        }
        return Arrays.asList(result.replaceAll("\\[|\\]", "").split(",\\s*"));
    }
}
