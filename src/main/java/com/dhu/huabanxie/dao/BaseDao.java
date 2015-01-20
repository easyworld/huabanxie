package com.dhu.huabanxie.dao;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.dhu.huabanxie.util.Page;

@Repository
public class BaseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public int insert(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	public int update(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	public int delete(String sql, Object... args) {
		return jdbcTemplate.update(sql, args);
	}

	public List<Map<String, Object>> queryForList(String sql, Object... args) {
		return jdbcTemplate.queryForList(sql, args);
	}

	public Map<String, Object> queryForMap(String sql, Object... args) {
		return jdbcTemplate.queryForMap(sql, args);
	}

	public Page queryForPage(String sql, int pageNo, int pageSize, Object... args) {
		String countQueryString = " select count(*) "
				+ removeSelect(removeOrders(sql));
		long totalCount = jdbcTemplate.queryForObject(countQueryString, args,
				Integer.class);
		if (totalCount < 1)
			return new Page();
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		String querySQL = "select * from (select a.*, rownum r from (" + sql
				+ ") a where rownum <= " + (startIndex + pageSize)
				+ ") where r > " + startIndex;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(querySQL,
				args);
		return new Page(startIndex, totalCount, pageSize, list);

	}

	private static String removeSelect(String sql) {
		Assert.hasText(sql);
		int beginPos = sql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, "sql: " + sql
				+ " must has a keyword 'from'");
		return sql.substring(beginPos);
	}

	private static String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find())
			m.appendReplacement(sb, "");
		m.appendTail(sb);
		return sb.toString();
	}
}
