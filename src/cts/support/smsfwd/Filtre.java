package cts.support.smsfwd;

public class Filtre {

	private int _id;
	private String _payload;
	private String _phone;
	private String _name;
	
	public Filtre()
	{
		return;
	}
	
	public Filtre(int id,String payload, String phone, String name)
	{
		_id = id;
		_phone = phone;
		_payload = payload;
		_name = name;
		return;
	}
	
	public void setId(int id)
	{
		_id = id;
		return;
	}
	
	public int getId()
	{
		return _id;
	}
	
	
	public void setPayload(String payload)
	{
		_payload = payload;
		return;
	}
	
	public String getPayload()
	{
		return _payload;
	}
	
	public void setPhone(String phone)
	{
		_phone = phone;
		return;
	}
	
	public String getPhone()
	{
		return _phone;
	}
	
	public void setName(String name)
	{
		_name = name;
		return;
	}
	
	public String getName()
	{
		return _name;
	}
	
}
