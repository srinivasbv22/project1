<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add Employee</title>
</head>
<body>
	<form action="addGun.html" method="post" >
		
		
        <fieldset >
            <table>
                
                <tr>
                    <td>
                            Name:
                    </td>
                    <td>
                            <input type="text" name="name" >
                    </td>
                </tr>
                <tr>
                    <td>
                            Model:
                    </td>
                    <td>
                            <input type="text" name="model">
                    </td>
                </tr>
                <tr>
                    <td>
                            Price:
                    </td>
                    <td>
                            <input type="text" name="price">                        
                    </td>
                </tr>
                <tr>
                        <td>
                                
                        </td>
                        <td>
                                <input type="submit" value="Confirm & Checkout">
                        </td>
                </tr>           
            </table>
        </fieldset>
	</form>
	<a href="index.html">Index</a>
</body>
</html>