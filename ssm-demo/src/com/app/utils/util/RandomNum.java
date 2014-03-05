/*
 * 文 件 名:  RandomNum.java
 * 描    述:  <描述>
 * 修 改 人:  caiqf
 * 修改时间:  2011-4-8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.app.utils.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import com.app.utils.constant.MD5;


/**
 * 随机数管理
 * 
 * @author caiqf
 * @version 1.0, 2011-4-8
 */
@SuppressWarnings("all")
public class RandomNum {
	private final static Random rm = new Random();

	private final static String[] stringNumber = { "01", "02", "03", "04",
			"05", "06", "07", "08", "09" };

	/**
	 * 字符串号码倒序
	 * 
	 * @param str
	 *            字符串
	 * @param sign
	 *            标识符号
	 * @return String [返回类型说明]
	 * @author caiqf
	 */
	public static String orderStr(String str, String sign) {
		String strArr[] = str.split("\\" + sign);
		int in, out;
		for (out = 1; out < strArr.length; out++) {
			int itemp = Integer.valueOf(strArr[out]).intValue();
			String stemp = strArr[out];
			in = out;
			while (in > 0
					&& Integer.valueOf(strArr[in - 1]).intValue() >= itemp) {
				strArr[in] = strArr[in - 1];
				--in;
			}
			strArr[in] = stemp;
		}
		for (int k = 0; k < strArr.length; k++) {
			if (k == 0) {
				str = strArr[k];
			} else {
				str += sign + strArr[k];
			}
		}
		return str;
	}

	/**
	 * 彩种选号随机生成
	 * 
	 * @param number
	 *            生成个数
	 * @param extent
	 *            生成的范围
	 * @param sign
	 *            中间追加符号
	 * @param isSingle
	 *            是否允许存在单个号码
	 * @param isSame
	 *            是否允许存在相同号码
	 * @return String [返回类型说明]
	 * @author caiqf
	 */
	public static String getRamdonNum(int number, int extent, String sign,
			boolean isSingle, boolean isSame) {
		String str = "";
		for (int j = 0; j < number; j++) {
			String tempString = "";
			while (true) {
				int tempInt = randomOneInt(extent + 1);
				tempString = String.valueOf(tempInt);
				if (!isSingle) {
					if (tempInt < 10) {// 随机生成数小于10
						if (tempInt == 0)
							tempInt = 1;
						tempString = stringNumber[tempInt - 1];
					}
				}
				if (isSame) {
					break;
				} else {
					if (str.indexOf(tempString) < 0) {
						break;
					}
				}
			}
			if (j == 0)
				str = tempString;
			else {
				str += sign + tempString;
			}
		}
		return str;
	}

	/**
	 * 彩种选号随机生成
	 * 
	 * @param count
	 *            注数 不需要时设置为1
	 * @param foreNum
	 *            前段生成个数 不需要时设置为0
	 * @param foreExtent
	 *            前段数量范围 不需要时设置为0
	 * @param afterNum
	 *            后段生成个数 不需要时设置为0
	 * @param afterExtent
	 *            后段数量范围 不需要时设置为0
	 * @param sign
	 *            中间追加符号
	 * @param isSingle
	 *            是否允许存在单个号码
	 * @param isSame
	 *            是否允许存在相同号码
	 * @return
	 */
	public static String getRamdonNum(int count, int foreNum, int foreExtent,
			int afterNum, int afterExtent, String sign, boolean isSingle,
			boolean isSame) {
		String str = "";
		for (int i = 0; i < count; i++) {
			String num = "";
			while (true) {
				if (foreExtent <= 0 && afterExtent > 0) {
					num = orderStr(getRamdonNum(afterNum, afterExtent, sign,
							isSingle, isSame), sign);
				} else if (foreExtent > 0 && afterExtent <= 0) {
					num = orderStr(getRamdonNum(foreNum, foreExtent, sign,
							isSingle, isSame), sign);
				} else {
					num += orderStr(getRamdonNum(foreNum, foreExtent, sign,
							isSingle, isSame), sign)
							+ "|"
							+ orderStr(getRamdonNum(afterNum, afterExtent,
									sign, isSingle, isSame), sign);
				}
				if (str.indexOf(num) < 0) {
					break;
				}
			}
			if (i == 0) {
				str = num;
			} else {
				str += "~" + num;
			}
		}
		return str;
	}

	/**
	 * 获得彩种随机排序后号码
	 * 
	 * @param count
	 *            注数 不需要时设置为1
	 * @param foreNum
	 *            前段生成个数 不需要时设置为0
	 * @param foreExtent
	 *            前段数量范围 不需要时设置为0
	 * @param afterNum
	 *            后段生成个数 不需要时设置为0
	 * @param afterExtent
	 *            后段数量范围 不需要时设置为0
	 * @param sign
	 *            中间追加符号
	 * @param isSingle
	 *            是否允许存在单个号码
	 * @param isSame
	 *            是否允许存在相同号码
	 * @return
	 */
	public static String getOrderByRamdonNum(int number, int extent,
			String sign, boolean isSingle, boolean isSame) {
		return orderStr(getRamdonNum(number, extent, sign, isSingle, isSame),
				sign);
	}

	/**
	 * 福彩3D组三
	 * 
	 * @param count
	 * @return [参数说明]
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 * @author caiqf
	 */
	public static String getRamdonNum(int count) {
		String str = "";
		for (int j = 0; j < count; j++) {
			String str2 = "";
			while (true) {
				int tempInt = randomOneInt(99) + 1;
				if (tempInt > 10) {
					str2 = String.valueOf(tempInt);
					int num = randomOneInt(2);
					str2 += str2.substring(num, num + 1);
					str2 = splitStr(str2, 1);
					if (frequency(str2) == 5) {
						str2 = orderStr(str2, ",");
						if (str.indexOf(str2) < 0) {
							break;
						}
					}
				}
			}
			if (j == 0)
				str = str2;
			else
				str += "~" + str2;
		}
		return str;
	}

	/**
	 * 福彩3D组六
	 * 
	 * @param count
	 * @return [参数说明]
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 * @author caiqf
	 */
	public static String getRamdonNum_zl(int count) {
		String str = "";
		for (int j = 0; j < count; j++) {
			String str2 = "";
			while (true) {
				int tempInt = randomOneInt(999) + 1;
				if (tempInt > 100) {
					str2 = splitStr(String.valueOf(tempInt), 1);
					if (frequency(str2) == 3) {
						str2 = orderStr(str2, ",");
						break;
					}
				}
			}
			if (j == 0)
				str = str2;
			else
				str += "~" + str2;
		}
		return str;
	}

	/**
	 * 随机生成一个整数
	 * 
	 * @param max
	 * @return
	 */
	public static int randomOneInt(int max) {
		return rm.nextInt(max);
	}

	/**
	 * 随机生成一个小于10的整数字符
	 * 
	 * @param max
	 * @return
	 */
	public static String randomOneString(int max) {
		return (new Integer(rm.nextInt(max))).toString();
	}

	/**
	 * 处理多注的机选号码
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @param splitType
	 *            分割符号
	 * @return [参数说明]
	 * @return List<String> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 * @author caiqf
	 */
	public static List<String> formatShowD(String str, String splitType,
			String title) {
		if (null != str && !str.isEmpty() && null != splitType
				&& !splitType.isEmpty()) {
			List<String> list = new ArrayList<String>();
			String[] temp = str.split("\\" + splitType);
			for (String string : temp) {
				list.add(title + string);
			}
			return list;
		}
		return null;
	}

	/**
	 * 处理多注的机选号码
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @param splitType
	 *            分割符号
	 * @return [参数说明]
	 * @return List<String> [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 * @author caiqf
	 */
	public static List<String> format(String str, String splitType) {
		if (null != str && !str.isEmpty() && null != splitType
				&& !splitType.isEmpty()) {
			List<String> list = new ArrayList<String>();
			String[] temp = str.split("\\" + splitType);
			for (String string : temp) {
				list.add(string);
			}
			return list;
		}
		return null;
	}

	/** 计算出现次数 */
	public static int frequency(String str) {
		int count = 0;
		String[] val = str.split(",");
		for (int i = 0; i < val.length; i++) {
			for (int j = 0; j < val.length; j++) {
				if (val[i].equals(val[j]))
					count++;
			}
		}
		return count;
	}

	/** 以长度来分割字符串 */
	public static String splitStr(String str, int length) {
		String restr = null;
		int count = str.length() / length;
		for (int i = 0; i < count; i++) {
			if (i == 0)
				restr = str.substring(0, length);
			else
				restr += "," + str.substring(0, length);
			str = str.substring(length, str.length());
		}
		return restr;
	}

	/**
	 * 处理多注的机选号码
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @return List<String>
	 */
	public static List<String> format(String str) {
		return format(str, "~");
	}

	/**
	 * 处理多注的机选号码
	 * 
	 * @param str
	 *            需要分割的字符串
	 * @return List<String>
	 */
	public static List<String> formatShow(String str, String title) {
		return formatShowD(str, "~", title);
	}

	/**
	 * 返回长度为length的字符串随机数
	 * 
	 * @param length
	 *            长度
	 */
	public static String randomForMedian(int length) {
		String str = "";
		for (int i = 0; i < length; i++) {
			int tempInt = randomOneInt(10);
			if (i == 0 && 0 == tempInt)
				tempInt = randomOneInt(9) + 1;
			str += tempInt;
		}
		return str;
	}

	/**
	 * @author caiqifan
	 * @date 2012-4-20 上午10:49:12
	 * @describe 随机生成MD5加密后的N位字符串(str-序列或规则字符串；n-N位字符串)
	 * @return String
	 * @exception
	 * @version 1.0
	 */
	public static String getUUID(String str, int n) {
		if (n > 32) {
			n = 32;
		}
		UUID uuid = UUID.randomUUID();
		String guid = str + "-" + uuid.toString();
		try {
			guid = MD5.getInstance()
					.getNBitMD5(
							new String(guid.replace("-", "").getBytes("UTF-8"),
									"UTF-8"), n);
			guid = guid.toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return guid;
	}

	/**
	 * @author caiqifan
	 * @date 2012-4-20 上午10:49:12
	 * @describe 随机生成MD5加密后的N位纯数字组合序列号，格式如(1111 2222 3333 4444)
	 * @return String
	 * @exception
	 * @version 1.0
	 */
	public static String getSerialNumberByMD5(String id, int n) {
		String guid = "";
		String str = id + RandomNum.randomForMedian((n - id.length()));
		if (null != str && !"".equals(str)) {
			String[] num = new String[str.length() / 4];
			for (int i = 0; i < num.length; i++) {
				num[i] = str.substring(0, 4);
				str = str.substring(4);
			}
			if (null != num && num.length > 0) {
				for (int i = 0; i < num.length; i++) {
					guid += num[i] + " ";
				}
				guid = guid.substring(0, guid.length() - 1);
			}
		}
		return guid;
	}

	/**
	 * @author caiqifan
	 * @date 2012-4-20 上午10:49:12
	 * @describe 随机生成N位纯数字组合序列号，格式如(11112222333344445555)
	 * @return String
	 * @exception
	 * @version 1.0
	 */
	public static String getSerialNumber(String id, int n) {
		String guid = "";
		if (null != id && !"".equals(id)) {
			guid = id + RandomNum.randomForMedian((n - id.length()));
		}
		return guid;
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-2 下午06:59:40
	 * @describe 去除一组字符串中重复的字符(数字组合)
	 * @return String[]
	 */
	public static String clearRepeatStr(String str, String sign) {
		String result = "";
		HashSet hs = new HashSet();
		if (null != str && !"".equals(str)) {
			String[] r = str.split(sign);
			for (int i = 0; i < r.length; i++) {
				hs.add(r[i]);
			}
			for (Iterator iterator = hs.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				result += object.toString() + sign;
			}
		}
		return result.substring(0, result.length() - 1);
	}

	/**
	 * 
	 * @author caiqf
	 * @date 2012-8-24 下午02:22:40
	 * @describe Double转Integer
	 * @return Integer
	 */
	public static Integer convertDoubleToInt(Double price, Integer num) {
		BigDecimal big = new BigDecimal(String.valueOf(price));
		return big.multiply(new BigDecimal(num)).intValue();
	}

	public static void main(String[] args) {
		// System.out.println(getRamdonNum(3,6, 32,3,16,",",false, false));
		// System.out.println(getRamdonNum(10, 9, ",", true, false));
		// System.out.println(getRamdonNum(1));
		// System.out.println(orderStr(getRamdonNum(5, 9,"|",false, false),
		// "|"));
		// System.out.println(getRamdonNum(5, 6, 35, 2, 12, ",", false, false));
		// System.out.println(getRamdonNum(3));
		// System.out.println(getRamdonNum_zl(3));
		// System.out.println(splitStr("123456", 2));
		// System.out.println(getRamdonNum(2));
		
		//生成激活码
		  // 随机数  
        Random random = new Random(); 
       
		 Set randomSet=new HashSet();
		 randomSet.add("ad");
		 randomSet.add("ad");
		 Set randomNum=new HashSet();
		 
		 for(int i=0;i<99999;i++){
			 int randomnum=random.nextInt(1000);
			 randomNum.add(randomnum);
		       
		        String sernumber=getSerialNumber(randomnum+"", 16);
		        randomSet.add(sernumber);
		 }
		 
		 System.out.println(randomNum.size());
		 System.out.println(randomSet.size());
		 
		 
		 int youNumber = 123456;

		//0 代表前面补充0
		//4 代表长度为4
		//d 代表参数为正数型
		 String str=String.format("%04d", youNumber);


		 System.out.println(str);
		 DecimalFormat df=new DecimalFormat("00000");
         String str2=df.format(123456789);
         System.out.println(str2);
         
		 
		// System.out.println(RandomNum.orderStr(RandomNum.clearRepeatStr("1,2,1,4,3,2,1",","), ","));
		//System.out.println(RandomNum.convertDoubleToInt(36.8, 100));
	}
}
