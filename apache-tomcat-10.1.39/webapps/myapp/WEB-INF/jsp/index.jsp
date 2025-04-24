<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Currency Converter</title>

    <style>
        .spinner {
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .spinner input {
            width: 60px;
            text-align: center;
        }

        .spinner button {
            width: 30px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="number"]::-webkit-outer-spin-button,
        input[type="number"]::-webkit-inner-spin-button {
            -webkit-appearance: none;
            margin: 0;
        }
        input[type="number"] {
            -moz-appearance: textfield;
        }
    </style>
</head>
<body>
<form name="currencyConverterForm" action="http://localhost:8080/myapp/calculateServlet" method="POST">
    <table>
        <tr>
            <td>Amount :</td>
            <td>
                <div class="spinner">
                    <button type="button" onclick="adjust(-1)">−</button>
                    <input type="number" step="any" name="amount" id="amount" value="${empty amount ? '0.00' : amount}" onchange="boundZero()"/>
                    <button type="button" onclick="adjust(1)">+</button>
                </div>
            </td>
        </tr>
        <tr>
            <td><label for="fromCurrency">From: </label></td>
            <td>
                <select id="fromCurrency" name="fromCurrency" required>
                    ${fromCurrencyOptions}
                </select>
            </td>
        </tr>
                <tr>
                    <td><label for="toCurrency">To: </label></td>
                    <td>
                        <select id="toCurrency" name="toCurrency" required>
                            ${toCurrencyOptions}
                        </select>
                    </td>
                </tr>
        <tr>
            <th><input type="submit" value="Submit" name="find"/></th>
            <th><input type="reset" value="Reset" name="reset" onclick="handleReset(event)" /></th>
        </tr>
    </table>
</form>
	<h2>Amount: <span id="result">${result}</span></h2>
	<h2>${error}</h2>
	    <script>
	        function boundZero() {
	            const input = document.getElementById("amount");
	            if(input.value <= 0) {
	                input.value = "0.00";
	            }
	        }
            function adjust(val) {
                const input = document.getElementById('amount');
                let current = parseFloat(input.value);
                if (isNaN(current)) {
                    current = 0;
                }
                current += val;
                input.value = current.toFixed(2);
                boundZero();
            }
            function handleReset(event) {
                event.preventDefault();

                document.getElementById("amount").value = "0.00";
                document.getElementById("fromCurrency").selectedIndex = 0;
                document.getElementById("toCurrency").selectedIndex = 0;
                document.getElementById("result").innerText = "";
            }
        </script>
</body>
</html>

