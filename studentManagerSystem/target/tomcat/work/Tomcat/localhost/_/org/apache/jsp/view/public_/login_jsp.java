/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2019-09-12 06:42:32 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.view.public_;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\" />\r\n");
      out.write("    <title>登录页面</title>\r\n");
      out.write("    <!--导入jQuery插件-->\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/easyui/jquery.min.js\"></script>\r\n");
      out.write("    <!--导入easyui框架-->\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/easyui/jquery.easyui.min.js\"></script>\r\n");
      out.write("    <!--汉化-->\r\n");
      out.write("    <script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/easyui/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("    <!--导入css文件-->\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/easyui/themes/default/easyui.css\">\r\n");
      out.write("    <!--导入图标css文件-->\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/easyui/themes/icon.css\">\r\n");
      out.write("\r\n");
      out.write("    <!--自定义css-->\r\n");
      out.write("\t    <style type=\"text/css\">\r\n");
      out.write("\t    \t/*input表单组件长宽设置*/\r\n");
      out.write("\t    \tinput{\r\n");
      out.write("\t    \t\twidth: 200px;\r\n");
      out.write("\t    \t\theight: 80px;\r\n");
      out.write("\t    \t}\r\n");
      out.write("\t    \tinput[name='rememberMe']{\r\n");
      out.write("\t    \t\twidth: 15px;\r\n");
      out.write("\t    \t\theight: 15px;\r\n");
      out.write("\t    \t}\r\n");
      out.write("\r\n");
      out.write("\t    \t/*账户输入框与密码输入框边框设置*/\r\n");
      out.write("\t    \t#inp1,#inp2{\r\n");
      out.write("\t    \t\tborder-top: hidden;\r\n");
      out.write("\t    \t\tborder-left: hidden;\r\n");
      out.write("\t    \t\tborder-right: hidden;\r\n");
      out.write("\t    \t\tborder-bottom: solid;\r\n");
      out.write("\t    \t}\r\n");
      out.write("\t    \t\r\n");
      out.write("\t    \t/*超链接样式设置*/\r\n");
      out.write("\t    \t.href{\r\n");
      out.write("\t    \t\ttext-decoration:none;\r\n");
      out.write("\t    \t}\r\n");
      out.write("\t    </style>\r\n");
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t\t$(function(){\r\n");
      out.write("\t\t\t\t$('#btn').linkbutton({    \r\n");
      out.write("\t\t\t\t\twidth:100,\r\n");
      out.write("\t \t\t\t\theight:30,\r\n");
      out.write("\t \t\t\t\ttext:\"登    录\",\r\n");
      out.write("\t\t\t\t});  \r\n");
      out.write("\t\t\t\t$(\"#btn\").click(function(){\r\n");
      out.write("\t\t\t\t\tif($(\":checkbox\").checked){\r\n");
      out.write("\t\t\t\t\t\t$(\":checkbox\").attr(\"value\",\"yes\");\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t$(\"#ff\").submit();\r\n");
      out.write("\t\t\t\t});   \r\n");
      out.write("\t\t\t})\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\r\n");
      out.write("\t<body  style=\"background-color: green;background-size: 100%,100%;\">\r\n");
      out.write("\t\t<div style=\"margin-top: 200px;\">\r\n");
      out.write("\t\t\t<div align=\"center\" style=\"margin-top: 10px;\">\r\n");
      out.write("\t\t\t\t<label style=\"font-family: '微软雅黑'; font-weight: bolder; font-size: 90;color: cadetblue;\">登录通道</label>\r\n");
      out.write("\t\t\t\t<form id=\"ff\" method=\"post\" action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/common/login\">\r\n");
      out.write("\t\t\t\t\t<table width=\"200\" cellspacing=\"5\">\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input id=\"inp1\"  class=\"easyui-textbox\" type=\"text\"  name=\"username\" value=\"\" data-options=\"iconCls:'icon-man',iconAlign:'left',prompt:'学号/职工号'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input id=\"inp2\" class=\"easyui-textbox\" type=\"password\"  placeholder=\"密码\" name=\"password\" value=\"\" data-options=\"iconCls:'icon-lock',iconAlign:'left',prompt:'首次登录密码:000'\"/></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<select class=\"easyui-combobox\" name=\"identify\" style=\"width:100px;height:30px;\" > \r\n");
      out.write("\t\t\t\t\t\t\t\t    <option value=\"student\" selected=\"true\">学生</option>   \r\n");
      out.write("\t\t\t\t\t\t\t\t    <option value=\"teacher\">教师</option>   \r\n");
      out.write("\t\t\t\t\t\t\t\t    <option value=\"admin\">管理员</option>   \r\n");
      out.write("\t\t\t\t\t\t\t\t</select> \r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"center\"><a id=\"btn\" href=\"#\"></a></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</div> \r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
