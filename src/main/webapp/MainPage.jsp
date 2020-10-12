<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Portfolio</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/css/bootstrap-datepicker.min.css" rel="stylesheet"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<style>
#positions {
	height: 200px;
	overflow-y: scroll;
}
.errorMessage {
	color: red;
}
</style>
</head>
<body>

	<div class="jumbotron text-center">
		<h1>CS310 Stock Portfolio Management</h1>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<h3>Graph</h3>
				<p>Graph will go here.</p>
			</div>

			<div class="col-sm-4">
				<h3>My Portfolio</h3>
				<div class="container" id="positions">
				</div>
				<button id="addStockModalButton" type="button" class="btn btn-primary">Add Stock</button>

				<div class="modal fade" id="addStockModal" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Add Stock</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label>Ticker Symbol</label> <input id="addStockTicker"
											class="form-control">
										<div id="addStockErrorTS" class="errorMessage"></div>
									</div>
									<div class="form-group">
										<label>Shares</label> <input id="addStockShares"
											class="form-control">
										<div id="addStockErrorShares" class="errorMessage"></div>
									</div>
									<div class="form-group">
										<label>Buy Date</label> <input id="addStockBuyDate"
											class="form-control">
										<div id="addStockErrorBuy" class="errorMessage"></div>
									</div>
									<div class="form-group">
										<label>Sell Date</label> <input id="addStockSellDate"
											class="form-control">
										<div id="addStockErrorSell" class="errorMessage"></div>
									</div>

								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" id="addStock" class="btn btn-primary">Add</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="deleteStockModal" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
						<div class="modal-header">
								<h5 class="modal-title">Are you sure you want to delete <span id="modalTickerSymbol"></span>?</h5>
							</div>
							<div class="modal-body">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancel</button>
								<button type="button" id="deleteStock" class="btn btn-primary">Delete Stock</button>
							</div>
						</div>
					</div>
				</div>			
				
			</div>
		</div>
	</div>
	
	<script>
	const finnhub_token = "bts376n48v6teecg7ul0";
	
		$(document).ready(
			function(){
				
				getPositions();
				
				// Data Picker Initialization
				$('#addStockBuyDate').datepicker({
					autoclose: true
				});
				
				$('#addStockSellDate').datepicker({
					autoclose: true
				});
				
				$('#addStock').click(
					function(e) {
						var tickerSymbol = $('#addStockTicker').val();
						var numShares = $('#addStockShares').val();
						var buyDate = $('#addStockBuyDate').val();
						var sellDate = $('#addStockSellDate').val();
						$('#addStockError').html("");
						
						addStock(tickerSymbol, numShares, buyDate, sellDate);		
					}
				);
				
				$('#deleteStock').click(
					function(e) {
						deleteStock();
					}
				);
				
				$('#addStockModalButton').click(
					function(e) {
						$('#addStockError').html("");
						$("#addStockModal").modal('show');
					}
				);
			}
		);
	
	// Populate portfolio list
	function getPositions() {
        var username = '<%= session.getAttribute("username")%>'
        let HTTP = new XMLHttpRequest();
        var d = new Date();
        var n = d.getTime();
        const url = "http://localhost:8080/portfolio?username=" + username.toString() + "&t=" + n;
        HTTP.open("GET", url);
        HTTP.send();
        
        $("#positions").html("");
        
        HTTP.onreadystatechange = (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		var response = JSON.parse(HTTP.responseText);
        		for(var i = 0; i < response.positions.length; i++){
        			var tickerSymbol = response.positions[i].position;
        			var row = "<div class='row'><div class='col-sm-2'><input type='checkbox'/></div><div class='col-sm-8'>" + tickerSymbol + 
        			"</div><div class='col-sm-2'><button type='button' class='btn' onclick=deleteStockModal('" + tickerSymbol + "')>X</button></div></div>";
                	$("#positions").append(row);
        		}
        	}   
        }
    }
	
	function deleteStockModal(tickerSymbol) {
		$("#modalTickerSymbol").html(tickerSymbol);
		$("#deleteStockModal").modal('show');
	}
	
	function deleteStock() {
		var tickerSymbol = $("#modalTickerSymbol").text();
		var username = '<%= session.getAttribute("username")%>'
		
		const HTTP = new XMLHttpRequest();
       	const url = "http://localhost:8080/portfolio?username=" + username.toString() + "&position=" + tickerSymbol.toString();
       	HTTP.open("DELETE", url);
       	HTTP.send();
       
       	HTTP.onreadystatechange = (e) => {
       		if(HTTP.readyState == 4 && HTTP.status == 200){
       			$("#deleteStockModal").modal('hide');
       			getPositions();
       		}   
       	}
	}
	
	function addStock(tickerSymbol, numShares, buyDate, sellDate){
		$('#addStockErrorTS').html("");
		$('#addStockErrorShares').html("");
		$('#addStockErrorBuy').html("");
		$('#addStockErrorSell').html("");
		
		const HTTP = new XMLHttpRequest();
	    const url = "https://finnhub.io/api/v1/quote?symbol=" + tickerSymbol.toString() + "&token=" + finnhub_token;
	    HTTP.open("GET", url);
	    HTTP.send();

    	HTTP.onreadystatechange = (e) => {
       		if(HTTP.readyState == 4 && HTTP.status == 200){
       			var error = false;
       			// invalid ticker symbol
       			if(HTTP.responseText.toString() == "{\"c\":0,\"h\":0,\"l\":0,\"o\":0,\"pc\":0,\"t\":0}"){
       				$('#addStockErrorTS').html("Invalid ticker symbol.");
       				error = true;
       			}
    			// number of shares is left blank
    			if(numShares == ""){
    				$('#addStockErrorShares').html("Please enter number of shares.");
    				error = true;
    			}
    			// number of shares is not an integer
    			else if(!Number.isInteger(Number(numShares))){
    				$('#addStockErrorShares').html("Please enter a whole number of shares.");
    				error = true;
    			}
    			// number of shares is less than or equal to 0
    			else if(numShares <= 0){
    				$('#addStockErrorShares').html("Number of shares must be greater than zero.");
    				error = true;
    			}
    			// both buy and sell date empty
    			if(!buyDate && !sellDate){
    				$('#addStockErrorBuy').html("Purchase date is required.");
    				error = true;
    			}
    			// buy date is empty, sell date is not
    			else if(!buyDate){
    				$('#addStockErrorBuy').html("Sold date without purchase date.");
    				error = true;
    			}
    			// sell date is before buy date
    			if(sellDate < buyDate){
    				$('#addStockErrorSell').html("Sold date is prior to purchase date.");
    				error = true;
    			}	
       				

       				// Get session attribute here 
       				var username = '<%= session.getAttribute("username")%>';

       	
       				
       			if(error == false){
       	            const HTTP = new XMLHttpRequest();
       	            const url = "http://localhost:8080/portfolio?username=" + username.toString() + "&position=" + tickerSymbol.toString() + "&share_count=" + 
       	            	numShares.toString() + "&date_bought=" + buyDate.toString() + "&date_sold=" + sellDate.toString();
       	            HTTP.open("POST", url);
       	            HTTP.send();
       	            
       	            HTTP.onreadystatechange = (e) => {
       	            	if(HTTP.readyState == 4 && HTTP.status == 200){
       	            		$("#addStockModal").modal('hide');
       	            		var row = "<div class='row'><div class='col-sm-2'><input type='checkbox'/></div><div class='col-sm-8'>" + tickerSymbol + 
       	        			"</div><div class='col-sm-2'><button type='button' class='btn' onclick=deleteStockModal('" + tickerSymbol + "')>X</button></div></div>";
       	                	$("#positions").append(row);
       	            	}   
       	            }	
       			}	
       		}
    	}
	}
	
	
	// Use to validate ticker symbol and get info for graph
	// https://finnhub.io/docs/api#stock-candles for historical data
	function getStockInfo() {
        var ticker = document.getElementById("ticker").value.toString();
        const HTTP = new XMLHttpRequest();
        const url = "https://finnhub.io/api/v1/quote?symbol=" + ticker + "&token=" + finnhub_token;
        HTTP.open("GET", url);
        HTTP.send();

        HTTP.onreadystatechange = (e) => {
            alert(HTTP.responseText.toString());
        }
    }
	
	
	</script>

</body>
</html>