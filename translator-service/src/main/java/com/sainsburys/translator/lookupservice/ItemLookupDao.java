package com.sainsburys.translator.lookupservice;

import com.sainsburys.translator.models.Item;

public interface ItemLookupDao {
	public Item getItemDetails(int upc) ;

}
