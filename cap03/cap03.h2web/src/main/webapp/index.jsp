
<html>
<head><title>First JSP</title></head>
<%@ page import="java.sql.*" %>
<body>
<h2>Hello World!</h2>
  <%
  Class.forName("org.h2.Driver");
  Connection conn = DriverManager.
      getConnection("jdbc:h2:~/testh2", "sa", "");
  out.println(conn.toString());
  conn.close();  
  %>
</body>
</html>

