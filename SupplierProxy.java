package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class SupplierProxy extends Proxy {

	static SupplierProxy _proxy = null;
	
	public SupplierProxy() 
	{
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {		
		LowQuantityProxy.GetProxy().placeOrder(orderDetails, buyer);
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}

	public static SupplierProxy GetProxy()
	{
		if (_proxy == null)
			_proxy = new SupplierProxy();
		
		return _proxy;
	}
}
