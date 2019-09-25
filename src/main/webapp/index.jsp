<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<html>
<body>
<h2>Scriptlets</h2>
<% 
	Date fecha = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	out.println(sdf.format(fecha));
	out.println("<br><strong>Cadena desde java</strong>");
%>
</body>
</html>
