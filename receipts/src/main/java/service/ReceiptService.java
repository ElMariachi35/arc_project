package service;

import javax.inject.Inject;

import model.Receipt;
import cdi.Service;
import dao.EntityDao;
import dao.ReceiptDao;

@Service
public class ReceiptService extends EntityService<Receipt> {

	@Inject
	private ReceiptDao receiptDao;

	@Override
	protected EntityDao<Receipt> getDao() {
		return receiptDao;
	}
}
