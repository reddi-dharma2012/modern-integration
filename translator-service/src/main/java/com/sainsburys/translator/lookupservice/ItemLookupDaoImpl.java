package com.sainsburys.translator.lookupservice;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.sainsburys.translator.models.Item;

@Repository
public class ItemLookupDaoImpl implements ItemLookupDao {
	
	private static final Logger log = LoggerFactory.getLogger(ItemLookupDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Item getItemDetails(int upc) {
		String GET_ITEM_SQL = "select * from Item where upc = ?";
		log.debug("UPC:"+upc);
		Item item = jdbcTemplate.queryForObject(GET_ITEM_SQL, new Object[] { upc }, new ItemMapper());
		return item;
	}

	class ItemMapper implements RowMapper<Item> {
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item item = new Item();
			if(rs!=null) {
			item.setSku(rs.getInt("sku"));
			item.setUpc(rs.getInt("upc"));
			item.setSkuDesc(rs.getString("sku_description"));
			}
			return item;
		}
	}

}
