package ca.uwo.proxies;

import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

public class LowQuantityProxy extends Proxy {
	/**
	 * constructor for WelcomeProxy class.
	 */
	
	static LowQuantityProxy _proxy = null;
	
	public LowQuantityProxy() {
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		if(orderDetails.size() > 10)
			HighQuantityProxy.GetProxy().placeOrder(orderDetails, buyer);
		else
		{
			
			Facade facade = new Facade();
			facade.placeOrder(orderDetails, buyer);
		}
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		Facade facade = new Facade();
		facade.restock(restockDetails, supplier);
	}
	
	public static LowQuantityProxy GetProxy()
	{
		if (_proxy == null)
			_proxy = new LowQuantityProxy();
		
		return _proxy;
	}

}