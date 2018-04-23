package org.nix.utils;

import java.net.SocketPermission;

public class XSSUtil {
	public static String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	public static void main(String[] args) {
		String html = " <form>\n" +
				"        <table class=\"input tabContent\">\n" +
				"            <tr>\n" +
				"                <th>\n" +
				"                    开始日期:\n" +
				"                </th>\n" +
				"                <td>\n" +
				"                    <input type=\"text\"  name=\"start\" class=\"text Wdate\" value=\"\" title=\"开始日期\" placeholder=\"不输入默认起始日期不限\" onfocus=\"WdatePicker({maxDate: '#F{$dp.$D(\\'endDate\\')}'});\" >\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <th>\n" +
				"                    结束日期:\n" +
				"                </th>\n" +
				"                <td>\n" +
				"                    <input type=\"text\" name=\"end\" class=\"text Wdate\" value=\"\" title=\"结束日期\" placeholder=\"不输入默认结束日期不限\" onfocus=\"WdatePicker({minDate: '#F{$dp.$D(\\'beginDate\\')}'});\" >\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <th>\n" +
				"                    导出订单数量:\n" +
				"                </th>\n" +
				"                <td>\n" +
				"                    <input type=\"text\"  class=\"text\"  name=\"number\" value=\"\" placeholder=\"不输入默认导出日期内全部\"/>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"            <tr>\n" +
				"                <td>\n" +
				"                    &nbsp;\n" +
				"                </td>\n" +
				"                <td>\n" +
				"                    <input type=\"button\" class=\"button\" onclick=\"\" value=\"确认导出\">\n" +
				"                    <input type=\"button\" class=\"button\" onclick=\"\" value=\"重置\">\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    </form>";
		System.out.println(htmlspecialchars(html));
	}
}
