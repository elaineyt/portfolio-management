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
<link class="include" rel="stylesheet" type="text/css" href="jquery.jqplot/jquery.jqplot.min.css" />
<!-- <link rel = "stylesheet" type = "text/css" href="StyleSheet.css"/> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="jquery.jqplot/jquery.jqplot.min.js"></script>
<script src="jquery.jqplot/plugins/jqplot.dateAxisRenderer.min.js"></script>
<script src="chartjs/Chart.min.js"></script>
<script src="chartjs/utils.js"></script>

<style>
#positions {
	height: 275px;
	overflow-y: scroll;
}
.errorMessage {
	color: red;
}
#top-right {
	position: absolute;
	top: 5px;
	right: 5px;
}
.position-padding {
	padding-top: 7px;
}
canvas{
	-moz-user-select: none;
	-webkit-user-select: none;
	-ms-user-select: none;
}

</style>
</head>
<body>
	<div class="topnav" style="padding-top: 2%; padding-bottom: 2.5%; margin-bottom: 25px; background-color: #787878;">
		<h1 style="color: #white; font-family:Lato; font-size:250%; padding-left: 7%;">USC CS310 Stock Portfolio Management </h1>
	</div>
	
	<div id="top-right">
		<button id="logoutButton" type="button" class="btn btn-primary">Logout</button>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-8">
				<h3>Graph</h3>
                <div id="graph" style="height:300px; width:650px;">
                	<canvas id="canvas"></canvas>
                </div>
                <div class="container">
                	<div class="row">
                		<div class="col-sm-3">
                    		<input type="text" size="10" id="graphStartDate" />
                    	</div>
                    	<div class="col-sm-6">
                    	</div>
                    	<div class="col-sm-3">
                    		<input type="text" size="10" id="graphEndDate" />
                    	</div>
                    </div>
                    <div class="row">
                    	<div class="col-sm-9">
                    	</div>
                    	<div class="col-sm-3">
                    		<span id='graphDateError' style='color:red; font-size: 10px;'></span>
                    	</div>
                    </div>
                </div>
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
	
	var graphEndDate = addDaysAndFormat(new Date(), 0);
	
	class Position {
		constructor(tickerSymbol, shares, buyDate, sellDate){
			this.tickerSymbol = tickerSymbol;
			this.shares = shares;
			this.buyDate = buyDate;
			this.sellDate = sellDate;
		}
	}
	
	var positions = new Map();
	
		$(document).ready(
			function(){
				
				getPositions();
				
				const idleDurationSecs = 120;    // X number of seconds
			    const redirectUrl = 'http://localhost:8080/index.jsp';  // Redirect idle users to this URL
			    let idleTimeout; // variable to hold the timeout, do not modify
			
			    const resetIdleTimeout = function() {
			
			        // Clears the existing timeout
			        if(idleTimeout) clearTimeout(idleTimeout);
			
			        // Set a new idle timeout to load the redirectUrl after idleDurationSecs
			        idleTimeout = setTimeout(() => window.location.href = redirectUrl, idleDurationSecs * 1000);
			    };
			
			    // Init on page load
			    resetIdleTimeout();
			
			    // Reset the idle timeout on any of the events listed below
			    ['click', 'touchstart', 'mousemove'].forEach(evt => 
			        document.addEventListener(evt, resetIdleTimeout, false)
			    );
				
				// Data Picker Initialization
				$('#addStockBuyDate').datepicker({
					autoclose: true
				});
				
				$('#addStockSellDate').datepicker({
					autoclose: true
				});
				
				$('#graphStartDate').datepicker({
					autoclose: true	
				})
				.on("change", function() {
					validateGraphDates();
				});
				
				
				$('#graphEndDate').datepicker({
					autoclose: true,
					endDate: addDaysAndFormat(new Date(), 0),
				})
				.on("change", function() {
					validateGraphDates();
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
				
				$('#logoutButton').click(
					function(e) {
						// * Send http request to server to overwrite session attribute.
						let HTTP = new XMLHttpRequest();
					    var d = new Date();
					    var n = d.getTime();
						const url = "http://localhost:8080/logout";
						HTTP.open("POST", url);
						HTTP.send();
						    
						HTTP.onreadystatechange = (e) => {
							if(HTTP.status == 200) {
							    // Successfully logged out user
							   	window.location.href = 'http://localhost:8080/index.jsp';
							}
						}
					}

				);
				
				// Try to redirect user
				try {
					var username = '<%= session.getAttribute("username")%>';
					
					// Redirect user if they are not logged in
					if(username == "") {
						window.location.href = 'http://localhost:8080/index.jsp';
					}	
					
					// Redirect user if they are not logged in
					if(<%= session.getAttribute("username").equals("") %>) {
						window.location.href = 'http://localhost:8080/index.jsp';
					}	
				} catch(e) {
					// username is not null so we want it as a string
					username = '<%= session.getAttribute("username")%>';
				}
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
        			var shareCount = response.positions[i].share_count;
        			var dateBought = response.positions[i].date_bought;
        			var dateSold = response.positions[i].date_sold;
        			var today = new Date();
        			if(Date.parse(dateBought) <= today && today <= Date.parse(dateSold)){
        				var row = "<div class='row' id='r" + tickerSymbol + "'><div class='col-sm-2 position-padding'><input type='checkbox' onclick='stockChecked(\"" + tickerSymbol + "\", this)'/></div><div class='col-sm-8 position-padding'>" + tickerSymbol + 
        				"</div><div class='col-sm-2'><button type='button' class='btn' onclick=deleteStockModal('" + tickerSymbol + "')>X</button></div></div>";
                		$("#positions").append(row);
                		positions.set(tickerSymbol, new Position(tickerSymbol, shareCount, formatDate(dateBought), formatDate(dateSold)));
        			}	
        		}
        		setDefaultGraphDates();
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
       			positions.delete(tickerSymbol);
				var index = stockHistoryLabels.indexOf(tickerSymbol);
        		config.data.datasets.splice(index, index+1);
    			window.myLine.update();
        		stockHistory.splice(index, 1);
        		stockHistoryLabels.splice(index, 1);
    			$("#r" + tickerSymbol).remove();
    			drawGraph();
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
       			else if(positions.has(tickerSymbol)){
       				$('#addStockErrorTS').html("Portfolio already contains this stock.");
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
       	        			var today = new Date();
       	            		if(Date.parse(buyDate.toString()) <= today && today <= Date.parse(sellDate.toString())){
       	            			var row = "<div class='row' id='r" + tickerSymbol + "'><div class='col-sm-2 position-padding'><input type='checkbox' onclick='stockChecked(\"" + tickerSymbol + "\", this)'/></div><div class='col-sm-8 position-padding'>" + tickerSymbol + 
       	        				"</div><div class='col-sm-2'><button type='button' class='btn' onclick=deleteStockModal('" + tickerSymbol + "')>X</button></div></div>";
       	                		$("#positions").append(row);
       	                		positions.set(tickerSymbol, new Position(tickerSymbol, numShares, buyDate, sellDate));
       	            		}        		
       	            	}   
       	            }	
       			}	
       		}
    	}
	}
	
	// keeps track of points used in graph
	var stockHistory = [];
	// keeps track of ticker symbols that correspond to points in stockHistory based on index
	var stockHistoryLabels = [];
	
	function drawGraph(tickerSymbol, index) {
		var colorName = colorNames[config.data.datasets.length % colorNames.length];
		var newColor = window.chartColors[colorName];
		var newDataset = {
			label: tickerSymbol,
			backgroundColor: newColor,
			borderColor: newColor,
			data: [],
			fill: false
		};
		

		for (var i= 0; i < config.data.labels.length; ++i) {
			newDataset.data.push(stockHistory[index][i]);
		}

		config.data.datasets.push(newDataset);
		window.myLine.update();
	}
	
	function getStockHistory(){
		return stockHistory;
	}
	
	function populateStockHistory(tickerSymbol) {
		var startDate = Date.parse($('#graphStartDate').val())/1000;
		var endDate = Date.parse($('#graphEndDate').val())/1000;
		
		const HTTP = new XMLHttpRequest();
        const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + tickerSymbol + "&resolution=D&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
        HTTP.open("GET", url);
        HTTP.send();

        HTTP.onreadystatechange = (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		var response = JSON.parse(HTTP.responseText);
        		var rawData = response.c;
        		var increment = Math.floor(rawData.length/5); // resolution can change
        		stockHistory.push([]);
        		var index = stockHistory.length-1;
        		stockHistoryLabels.push(tickerSymbol);
        		for(var i = 0; i < rawData.length; i+=increment){	
        			stockHistory[index].push(rawData[i]);
        		}
        		
        		drawGraph(tickerSymbol, index);
        	}
        }
        
    }


    function stockChecked(tickerSymbol, checkBox){
    	// if unchecked, remove from stock history and redraw
    	if(!checkBox.checked){
    		var index = stockHistoryLabels.indexOf(tickerSymbol);
    		config.data.datasets.splice(index, index+1);
			window.myLine.update();
    		stockHistory.splice(index, 1);
    		stockHistoryLabels.splice(index, 1);
    		drawGraph();
    	}
    	else {
    		populateStockHistory(tickerSymbol);
    	}
    }
    
    
    function getEarliestBuyDate(){
    	var earliestBuyDate = addDaysAndFormat(new Date(), 0);
    	for(let [key, value] of positions){
    		if(value.buyDate < earliestBuyDate){
    			earliestBuyDate = value.buyDate;
    		}
    	}
    	return earliestBuyDate;
    }
    
    function formatDate(date){
    	var dateParts = date.split("-");
    	return [dateParts[1], dateParts[2], dateParts[0]].join('/');
    }
    
    function addDaysAndFormat(date, days) {
        var d = new Date(date);
        d.setDate(d.getDate() + days);
        var month = '' + (d.getMonth() + 1);
        var day = '' + d.getDate();
        var year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [month, day, year].join('/');
    }

    function DaysBetween(date1, date2) {
        var d1 = new Date(date1);
        var d2 = new Date(date2);

        var diff = d2.getTime() - d1.getTime();

        return diff / (1000 * 3600 * 24); 
    }
    
    function setDefaultGraphDates(){
    	$('#graphStartDate').datepicker("update", getEarliestBuyDate());
		$('#graphEndDate').datepicker("update", addDaysAndFormat(new Date(), 0));
    }
    
    function validateGraphDates() {
    	if($('#graphEndDate').val() != "" && $('#graphEndDate').val() < $('#graphStartDate').val()){
    		$('#graphDateError').html('End date before start date');
    		$('#graphEndDate').datepicker("update", graphEndDate);	
    	}
    	else{
    		graphEndDate = $('#graphEndDate').val();
    	}
    }
    
    
	
	
	</script>
	<script>
		var lab = ['1', '2', '3', '4', '5', '6']
		var config = {
			type: 'line',
			data: {
				labels: lab,
				datasets: []
			},
			options: {
				responsive: true,
				title: {
					display: false,
					text: ''
				},
				tooltips: {
					mode: 'index',
					intersect: false,
				},
				hover: {
					mode: 'nearest',
					intersect: true
				},
				scales: {
					xAxes: [{
						display: true,
						scaleLabel: {
							display: true,
							labelString: 'Date'
						}
					}],
					yAxes: [{
						display: true,
						scaleLabel: {
							display: true,
							labelString: 'Dollars'
						}
					}]
				}
			}
		};

		window.onload = function() {
			var ctx = document.getElementById('canvas').getContext('2d');
			window.myLine = new Chart(ctx, config);
		};

		var colorNames = Object.keys(window.chartColors);


	</script>
	


</body>
</html>