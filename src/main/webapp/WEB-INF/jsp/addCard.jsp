<%@include file="common/header.jsp"%>
<form action="/addCard" method="post">
    <label>Credit card number</label> <input type="text" name="number" placeholder="1111-2222-3333-4444" >
    <label>Exp Date</label> <input type="text" name="expDate" placeholder="01/2023">
    <label>CVV</label> <input type="text" name="cvv" placeholder="123">
    <input type="submit" value="Add Credit Card">
</form>
<%@include file="common/footer.jsp"%>