<%@include file="common/header.jsp"%>
<form action="/addCard" method="post">
    <label>Credit card number</label> <input type="text" name="number" placeholder="4111-2222-3333-4444" pattern="[4-5]\d{3}-\d{4}-\d{4}-\d{4}" required>
    <label>Exp Date</label> <input type="text" name="expDate" placeholder="01/2023" pattern="\d{2}/\d{4}" required>
    <label>CVV</label> <input type="text" name="cvv" placeholder="123" pattern="\d{3}" required>
    <label>Card Holder Name</label> <input type="text" name="cardHolderName" placeholder="John Doe" pattern="[a-zA-Z\s]+" required>
    <input type="submit" value="Add Credit Card">
</form>
<%@include file="common/footer.jsp"%>