<html>
<body>
<h2>Hello World!</h2>
  <%
    double num = Math.random();
    if (num > 0.50) {
  %>
      <h2>Que pases un buen dí&iacute;a</h2><p>(<%= num %>)</p>
  <%
    } else {
  %>
      <h2>Todo va bien... </h2><p>(<%= num %>)</p>
  <%
    }
  %>
  <a href="<%= request.getRequestURI() %>"><h3>Empezamos de nuevo</h3></a>
  </body>
</html>
