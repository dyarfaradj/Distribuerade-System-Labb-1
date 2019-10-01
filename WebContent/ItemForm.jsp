<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html>
<html>
<head>
 <title>Item Management Application</title>
</head>
<body>

    <div align="center">  
   <form action="<%=request.getContextPath()%>/insert" method="post">
        <table border="1" cellpadding="5">
           <input type="hidden" name="id"/>
 	         <tr>
                <th>item Title: </th>
                <td>
                 <input type="text" name="title" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Item description: </th>
                <td>
                 <input type="text" name="description" size="45"/>
                </td>
            </tr>
            <tr>
                <th>quantity: </th>
                <td>
                 <input type="text" name="quantity" size="15"/>"
                 />
                </td>
            </tr>
            <tr>
                <th>price: </th>
                <td>
                 <input type="text" name="price" size="15"
                 />
                </td>
            </tr>
            <tr>
             <td colspan="2" align="center">
              <input type="submit" value="Submit" />
             </td>
            </tr>
        </table>
        </form>
    </div> 
</body>
</html>