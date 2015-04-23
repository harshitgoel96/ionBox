package com.harshit.ionBox.DB;

public class CacheModel {
	private String key;
	private String value;

	public String getKey() {
		return this.key;
	}

	public void setKey(String _key) {
		this.key = _key;
	}
	public String getValue(){
		return this.value;
	}
	public void setValue(String _value)
	{
		this.value=_value;
	}
	@Override public String toString()
	{
		return key;
	}
}
