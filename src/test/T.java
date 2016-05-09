package test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class T {
	static List<String> paraseFootRingNum(String footRingNum) {
		List<String> footList = new ArrayList<String>();
		String[] foots = footRingNum.split("-");
		int len = foots.length - 1;
		String foot = foots[len];
		footList.add(foot);
		for (int i = len - 1; i >= 0; i--) {
			foot = foots[i] + "-" + foot;
			footList.add(foot);
		}
		return footList;
	}

	public static void main(String[] args) throws ParseException {
		List<String> foots = paraseFootRingNum("abc-ac-1234");
		for (String f : foots) {
			System.out.println(f);
		}
		/*
		 * Date date1=DateUtil.getYearFirst(2014); Date
		 * date2=DateUtil.getYearLast(2014);
		 * 
		 * SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 * System.out.println(df.format(date1));
		 * System.out.println(df.format(date2));
		 */
		/* Date date=df.parse("2014-08-31"); */

		/*
		 * Calendar calendar= Calendar.getInstance(); calendar.setTime(date);
		 * calendar.add(Calendar.DATE, 100); Date date2=calendar.getTime();
		 * System.out.println(df.format(date2));
		 */
		// TODO Auto-generated method stub
		/*
		 * String ss=" 12羽 1"; Pattern p=Pattern.compile("(\\d+)"); Matcher
		 * m=p.matcher(ss); if(m.find()){ System.out.println(m.group()); }
		 */
		/*
		 * MUserInfo userInfo = new MUserInfo(); List l=new ArrayList();
		 * l.add(userInfo); userInfo.setAccount("abc"); JsonConfig config =new
		 * JsonConfig(); config.setJsonPropertyFilter(new PropertyFilter() {
		 * public boolean apply(Object source, String name, Object value) {
		 * return value==null; } }); JSONArray obj =
		 * JSONArray.fromObject(userInfo,config); System.out.println(obj);
		 */
		/*
		 * String str="105度45分31.4秒<br>32度24分58.9秒"; String[]
		 * gStrs=str.split("<br>"); String latitudeStr=gStrs[0]; Double
		 * latitude=gps2double(latitudeStr); if(latitude!=null){
		 * System.out.println(latitude); }
		 */

		/*
		 * MRaceChose ch=new MRaceChose(); ch.setPreDate(new Date());
		 * MyPage<MRaceChose> mypage= new MyPage<MRaceChose>();
		 * mypage.setTotal(1111); List<MRaceChose> list=new
		 * ArrayList<MRaceChose>(); list.add(ch); mypage.setRows(list);
		 * JSONObject json=JSONUtil.fromObjectIgNull(mypage);
		 * System.out.println(json);
		 */
	}

	private static Double gps2double(String latitudeStr) {
		if (latitudeStr != null && !latitudeStr.isEmpty()) {
			int du = latitudeStr.indexOf("度");
			if (du != -1) {
				double latitude = 0;
				String duStr = latitudeStr.substring(0, du);
				latitude = Double.valueOf(duStr);
				int min = latitudeStr.indexOf("分");
				if (min != -1) {
					String minStr = latitudeStr.substring(du + 1, min);
					double minValue = Double.valueOf(minStr);
					latitude += minValue / 60.0;
				}
				int sec = latitudeStr.indexOf("秒");
				if (sec != -1) {
					String secStr = latitudeStr.substring(min + 1, sec);
					double secValue = Double.valueOf(secStr);
					latitude += secValue / 3600.0;
				}
				System.out.println(latitude);
				return latitude;
			}
		}
		return null;
	}

}
