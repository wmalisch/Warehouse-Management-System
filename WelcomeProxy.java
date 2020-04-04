package ca.uwo.proxies;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This is one concrete implementation of {@link ca.uwo.proxies.Proxy} base class, it is the first proxy
 * the {@link ca.uwo.client.Client} will encounter. If the request of client is not issued by this class, 
 * it is forwarded to the {@link ca.uwo.proxies.SupplierProxy}, then {@link ca.uwo.proxies.LowQuantityProxy}, 
 * lastly {@link ca.uwo.proxies.HighQuantityProxy}. The link between those proxies implements Chain of Responsibility 
 * design pattern.
 */
public class WelcomeProxy extends Proxy {
	/**
	 * constructor for WelcomeProxy class.
	 */
	
	static WelcomeProxy _proxy = null;
	static public Map<Integer, Buyer> _buyers = null;
	
	public WelcomeProxy() 
	{
		
	}

	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#placeOrder(java.util.Map, ca.uwo.client.Buyer)
	 */
	@Override
	public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
		
		if(Authenticate(buyer))
		{
			System.out.println(buyer.getUserName() + " was authenticated.");		
			SupplierProxy.GetProxy().placeOrder(orderDetails, buyer);
		}
		else
			System.out.println(buyer.getUserName() + " failed to authenticate.");		
			
	}

	public boolean Authenticate(Buyer buyer)
	{
		for (Map.Entry<Integer,Buyer> entry : _buyers.entrySet())
			if (entry.getValue().getUserName() == buyer.getUserName() &&
					entry.getValue().getPassword() == buyer.getPassword())
				return true;
		
		return false;
	}
	
	/* (non-Javadoc)
	 * @see ca.uwo.frontend.interfaces.FacadeCommands#restock(java.util.Map, ca.uwo.client.Supplier)
	 */
	@Override
	public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
		SupplierProxy.GetProxy().restock(restockDetails, supplier);
	}

	public static WelcomeProxy GetProxy()
	{
		if (_proxy == null)
			_proxy = new WelcomeProxy();
		
		return _proxy;
	}

	public void SetBuyers(Map<Integer, Buyer> buyers) {
		// TODO Auto-generated method stub
		_buyers = buyers;
	}
}
