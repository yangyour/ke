package cn.dblearn.dbblog.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 工作日工具类
 * @author lxh
 * @date   2019-05-13
 */
public class WorkDayUtil {

	public static void main(String[] args) {
//		String time = "2019-02-18 12:45:00";
		List<String> holidayList = new ArrayList<>();
		holidayList.add("2019-08-07");
		holidayList.add("2019-08-08");
		List<String> workdayList = new ArrayList<>();
		workdayList.add("2019-08-10");
		workdayList.add("2019-08-11");
		int days = 7;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date c = getWorkDays(new Date(),workdayList,holidayList,days);
		System.out.println(c);
	}

	/**
	 * 获得指定日期之后的工作日日期时间（周末、节假日除外）
	 * @param time			传入的日期时间
	 * @param workdayList   指定日期的工作日列表
	 * @param holidayList   指定日期的假期列表
	 * @param days          相隔天数
	 */
	public static Date getWorkDays(Date time,List<String> workdayList,List<String> holidayList,int days) {
		Calendar ca = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		try {
			List<Calendar> workList = initHoliday(workdayList);
			List<Calendar> holiList = initHoliday(holidayList);
//			Date d = df.parse(time);
			ca.setTime(time);//设置当前时间
			c = addDateByWorkDay(ca,workList,holiList,days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTime();
	}

	/**
	 * 初始化假期格式
	 * @param list 假期列表
	 * @return
	 */
	public static List<Calendar> initHoliday(List<String> list) {
		List holidayList = new ArrayList();
		if (list != null && list.size() > 0) {
			for (String dateTime : list) {
				String [] da = dateTime.split("-");

				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
				calendar.set(Calendar.MONTH, Integer.valueOf(da[1])-1);//月份比正常小1,0代表一月
				calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
				holidayList.add(calendar);
			}
		}
		return holidayList;
	}

	/**
	  *
	  * <p>Title: addDateByWorkDay </P>
	  * <p>Description: TODO  计算相加day天，并且排除节假日和周末后的日期</P>
	  * @param calendar  当前的日期
	  * @param workdayList   指定日期的工作日列表
	  * @param holidayList   指定日期的假期列表
	  * @param day  相加天数
	  * @return 返回类型   返回相加day天，并且排除节假日和周末后的日期
	  */
	 public static Calendar addDateByWorkDay(Calendar calendar,List<Calendar> workdayList,List<Calendar> holidayList,int day){

		 try {
			for (int i = 0; i < day; i++) {

				 calendar.add(Calendar.DAY_OF_MONTH, 1);

				 if(checkHoliday(calendar,workdayList,holidayList)){
					 i--;
				 }
			}
			return calendar;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendar;
	 }

	 /**
	  *
	  * <p>Title: checkHoliday </P>
	  * <p>Description: TODO 验证日期是否是节假日</P>
	  * @param calendar  传入需要验证的日期
	  * @return 返回类型  返回true是节假日，返回false不是节假日
	  */
	 public static boolean checkHoliday(Calendar calendar,List<Calendar> workdayList,List<Calendar> holidayList) throws Exception{

		 //判断日期是否是工作日
		 for (Calendar cen : workdayList) {
			if(cen.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
					cen.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&
							cen.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
				return false;
			}
		}

		 //判断日期是否是周六周日
		 if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
				 calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			 return true;
		 }
		 //判断日期是否是节假日
		 for (Calendar ca : holidayList) {
			if(ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
					ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&&
					ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)){
				return true;
			}
		}

		 return false;
	 }

}
