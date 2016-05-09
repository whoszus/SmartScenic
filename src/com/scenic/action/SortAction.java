package com.scenic.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.scenic.baseUitl.CharacterConversion;
import com.scenic.baseUitl.WriteJson;
import com.scenic.service.impl.SortServiceImpl;

public class SortAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private SortServiceImpl sImpl;
	private String cityName;

	// http://luorong:8080/SmartScenic/sort/sort_weekSortByAqi.action?cityName=绵阳市
	public void weekSortByAqi() {
		System.out.println(CharacterConversion.toUTF8(cityName));
		String str = sImpl.sortByAqi(7, CharacterConversion.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_weekSortBYComf.action?cityName=绵阳市
	public void weekSortBYComf() {
		String str = sImpl.sortByComfortLevel(7, CharacterConversion
				.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_weekSortBYComprehensive.action?cityName=绵阳市
	public void weekSortBYComprehensive() {
		String str = sImpl.sortByComprehensive(7, CharacterConversion
				.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_monthSortByAqi.action?cityName=绵阳市
	public void monthSortByAqi() {
		String str = sImpl.sortByAqi(30, CharacterConversion.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_monthSortBYComf.action?cityName=绵阳市
	public void monthSortBYComf() {
		String str = sImpl.sortByComfortLevel(30, CharacterConversion
				.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_monthSortBYComprehensive.action?cityName=绵阳市
	public void monthSortBYComprehensive() {
		String str = sImpl.sortByComprehensive(30, CharacterConversion
				.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_yerterdaySortByAqi.action?cityName=绵阳市
	public void yerterdaySortByAqi() {
		String json = sImpl.sortByAqi(1, CharacterConversion.toUTF8(cityName))
				.toString();
		WriteJson.writeToClient(json);
	}

	// http://luorong:8080/SmartScenic/sort/sort_sortYesterdayBycomfertable.action?cityName=绵阳市
	public void sortYesterdayBycomfertable() {
		String str = sImpl.sortByComfortLevel(1, CharacterConversion
				.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	// http://luorong:8080/SmartScenic/sort/sort_sortYesterdayByComprehensive.action?cityName=绵阳市
	public void sortYesterdayByComprehensive() {
		String str = sImpl.sortByComprehensive(1, CharacterConversion
				.toUTF8(cityName));
		WriteJson.writeToClient(str);
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
