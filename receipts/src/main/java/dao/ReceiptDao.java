package dao;

import cdi.Dao;
import model.Receipt;

@Dao
public class ReceiptDao extends EntityDao<Receipt>{

	@Override
	protected Class<Receipt> getResponseClass() {
		return Receipt.class;
	}

}
