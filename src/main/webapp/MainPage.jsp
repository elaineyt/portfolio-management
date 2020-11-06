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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
#historicalPositions {
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
		<h1 style="color: #white; font-family:Lato; font-size:250%; padding-left: 7%;">USC CS310 <br> Stock Portfolio Management </h1>
	</div>
	
	<div id="top-right">
		<button id="logoutButton" type="button" class="btn btn-primary">Logout</button>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-sm-6">
				<div class="row">
                		<div class="col-sm-5">
                    		<span id="currentPortfolioValue" style='font-size: 40px;'></span>
                    		&nbsp;
                    		<span id="currentPortfolioValueChange" style='font-size: 20px;'></span>
                    		<span id="redDownTriangle" style="color:red;font-size:30px;display:none;"><i class="fa fa-caret-down"></i></span>
                    		<span id="greenUpTriangle" style="color:green;font-size:30px;display:none;"><i class="fa fa-caret-up"></i></span>
                    	</div>
                    	<div class="col-sm-4">
                    	</div>
                    	<div class="col-sm-3">
                    		<div class="row" style='margin:auto;display:flex;flex-wrap:wrap;justify-content:center;'>
	                    		<label style='margin:5px auto;' for="graphUnits">Unit</label>
								<select style='margin:5px;' name="graphUnits" id="graphUnits">
	  							<option value="days">Days</option>
	  							<option value="weeks">Weeks</option>
								</select>
							</div>
							<div class="row" style='margin:auto;padding-top:10px;display:flex;flex-wrap:wrap;justify-content:center;'>
								<button id="zoomIn" type="button" class="btn btn-primary" style='margin-right:5px;'>+</button>
								<button id="zoomOut" type="button" class="btn btn-primary">-</button>
                    		</div>
                    	</div>
                    </div>
                <div id="graph" style="height:300px; width:auto;">
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
                		<div class="col-sm-3" style='font-size: 12px;'>
                    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Start Date
                    	</div>
                    	<div class="col-sm-6">
                    	</div>
                    	<div class="col-sm-3" style='font-size: 12px;'>
                    		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End Date
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

			<div class="col-sm-3">
				<h3>My Portfolio</h3>
				<div class="container" id="positions">
				</div>
				<div class='row' style='margin-top:10px;margin-left:0px;' id='add-stock-button-row'>
					<button id="addStockModalButton" type="button" class="btn btn-primary">Add Stock</button>
				</div>
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
										<label>Quantity</label> <input id="addStockShares"
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
				
				<button id="bulkEditModalButton" type="button" class="btn btn-primary">Bulk Edit</button>
				<div class="modal fade" id="bulkEditModal" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Bulk Edit</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
  								
    								<p>CSV File</p>
    								
    								<input type="file" id="myFile" name="file" accept=".txt, .csv">
    								<div id="bulkStockError" class="errorMessage">
    								</div>
    								<div class="mt-3">
    									<button type="button" class="btn btn-secondary" id="closeButton"
											data-dismiss="modal">Cancel</button>
      									<button type="submit" id="submitBulk" class="btn btn-primary">Upload File</button>
    								</div>
  								</form>
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
			
			<div class="col-sm-3">
				<h3>Historical Trends</h3>
				<div class="container" id="historicalPositions">
				</div>
				<div class='row' style='margin-top:10px;margin-left:0px;' id='view-stock-button-row'>
					<button id="addHistoricalStockModalButton" type="button" class="btn btn-primary">View Stock</button>
				</div>
				<div class="modal fade" id="addHistoricalStockModal" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">View Stock</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
										<label>Ticker Symbol</label> <input id="addHistoricalStockTicker"
											class="form-control">
										<div id="addHistoricalStockErrorTS" class="errorMessage"></div>
									</div>
									<div class="form-group">
										<label>Quantity</label> <input id="addHistoricalStockShares"
											class="form-control">
										<div id="addHistoricalStockErrorShares" class="errorMessage"></div>
									</div>
									<div class="form-group">
										<label>Buy Date</label> <input id="addHistoricalStockBuyDate"
											class="form-control">
										<div id="addHistoricalStockErrorBuy" class="errorMessage"></div>
									</div>
									<div class="form-group">
										<label>Sell Date</label> <input id="addHistoricalStockSellDate"
											class="form-control">
										<div id="addHistoricalStockErrorSell" class="errorMessage"></div>
									</div>

								</form>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Close</button>
								<button type="button" id="addHistoricalStock" class="btn btn-primary">Add</button>
							</div>
						</div>
					</div>
				</div>
				
				<div class="modal fade" id="deleteHistoricalStockModal" tabindex="-1"
					role="dialog" aria-hidden="true">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
						<div class="modal-header">
								<h5 class="modal-title">Are you sure you want to delete <span id="historicalModalTickerSymbol"></span>?</h5>
							</div>
							<div class="modal-body">
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Cancel</button>
								<button type="button" id="deleteHistoricalStock" class="btn btn-primary">Delete Stock</button>
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
	Number of points on graph: <div id="graphPoints">0</div>
	Yesterday's total portfolio value: <div id="yesterdayPortfolioPoint">0</div>
	Today's total portfolio value: <div id="todayPortfolioPoint">0</div>
	
	<script>
	var graphPoints = 0;
	
	const finnhub_token = "bts376n48v6teecg7ul0";
	
	var graphEndDate = addDaysAndFormat(new Date(), 0);
	var graphStartDate = addDaysAndFormat(new Date(), -92);
	
	class Position {
		constructor(tickerSymbol, shares, buyDate, sellDate){
			this.tickerSymbol = tickerSymbol;
			this.shares = shares;
			this.buyDate = buyDate;
			this.sellDate = sellDate;
		}
	}
	
	var positions = new Map();
	var historicalPositions = new Map();
	var earliestStartDates = new Map();
	var zooming_graph_lock = false;
	var initial_request = false;
	
		$(document).ready(
			function(){

				$('#graphStartDate').datepicker({
					autoclose: true,
          			startDate: addDaysAndFormat(new Date().setFullYear(new Date().getFullYear() - 1), 0)
				});
				
				$('#graphEndDate').datepicker({
					autoclose: true,
					endDate: addDaysAndFormat(new Date(), 0),
				});
				
				setDefaultGraphDates();
				
				$('#graphStartDate').on("change", function() {
					validateGraphDates('start-date')
				});
				
				$('#graphEndDate').on("change", function() {
					validateGraphDates('end-date')
				});
				
				// * Get stock positions
				getPositions();
				
				// * Get historical positions
				getHistoricalPositions();
				
				// * Check timeout
				const idleDurationSecs = 120;    // X number of seconds
			    const redirectUrl = 'https://localhost:8443/index.jsp';  // Redirect idle users to this URL
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
				
				$('#addHistoricalStockBuyDate').datepicker({
					autoclose: true
				});
				
				$('#addHistoricalStockSellDate').datepicker({
					autoclose: true
				});
				
				$('#zoomIn').click(
						function(e) {
							zooming_graph_lock = true;
							if($('#graphUnits').val() == "weeks"){
								var end_date_new = $('#graphEndDate').datepicker('getDate'); 
							    end_date_new.setDate(end_date_new.getDate () - 7); 
							    $('#graphEndDate').datepicker ('setDate', end_date_new);
							    
							    var start_date_new = $('#graphStartDate').datepicker('getDate'); 
							    start_date_new.setDate(start_date_new.getDate () + 7); 
							    $('#graphStartDate').datepicker ('setDate', start_date_new);
							} else {
								var end_date_new = $('#graphEndDate').datepicker('getDate'); 
							    end_date_new.setDate(end_date_new.getDate () - 1); 
							    $('#graphEndDate').datepicker ('setDate', end_date_new);
							    
							    var start_date_new = $('#graphStartDate').datepicker('getDate'); 
							    start_date_new.setDate(start_date_new.getDate () + 1); 
							    $('#graphStartDate').datepicker ('setDate', start_date_new);
							}
							
							
							getPositions();  
					    	getHistoricalPositions();
					    	zooming_graph_lock = false;
						}
					);
				
				$('#zoomOut').click(
						function(e) {
							zooming_graph_lock = true;
							let earliest_start_date = new Date().setFullYear(new Date().getFullYear() - 1);
							if($('#graphUnits').val() == "weeks"){
								var end_date_new = $('#graphEndDate').datepicker('getDate'); 
								if(end_date_new.getDate() + 7 <= new Date().getDate()) {
								    end_date_new.setDate(end_date_new.getDate() + 7); 
								    $('#graphEndDate').datepicker('setDate', end_date_new);	
								}
								
							    var start_date_new = $('#graphStartDate').datepicker('getDate'); 
							    var earliest_plus_seven = earliest_start_date + 604800000; // Number of milliseconds for 7 days
								if(start_date_new > new Date(earliest_plus_seven)) {
								    start_date_new.setDate(start_date_new.getDate () - 7); 
							    	$('#graphStartDate').datepicker ('setDate', start_date_new);
								}
							} else {
								var end_date_new = $('#graphEndDate').datepicker('getDate'); 
								if(end_date_new.getDate() + 1 <= new Date().getDate()) {
								    end_date_new.setDate(end_date_new.getDate() + 1); 
								    $('#graphEndDate').datepicker ('setDate', end_date_new);	
								}
								
							    var start_date_new = $('#graphStartDate').datepicker('getDate'); 
							    if(start_date_new > new Date(earliest_start_date)) {
								    start_date_new.setDate(start_date_new.getDate () - 1); 
							    	$('#graphStartDate').datepicker ('setDate', start_date_new);
								}
							}	
							
							
							getPositions();  
					    	getHistoricalPositions();
					    	zooming_graph_lock = false;
						}
					);
				
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
				
				$('#submitBulk').click(function(){
					$('#bulkStockError').html("");
					const files = $('#myFile').get(0).files
					var today = new Date();
					var dd = String(today.getDate()).padStart(2, '0');
					var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
					var yyyy = today.getFullYear();
					today = mm + '/' + dd + '/' + yyyy;
					console.log(today);
					if(files.length == 0)
					{
						return false;
					}
					else
				    {
						console.log(files);
// 						var suffix = files[0].split(".");
// 						if((suffix[1] != "txt") || (suffix[1] != "csv"))
// 						{
// 							//error bad file format
// 							console.log("bad file format");
// 							return false;
// 						}
						const file = files[0];
	 					const reader = new FileReader()
	 					reader.onload = function(event)
	 					{
	 						var stocks = event.target.result.split("\n");
// 	 						for(var i = 0; i < stocks.length; i++)
// 	 						{
// 	 							console.log("HIT");
// 	 							var stock = stocks[i].split(",");
// 	 							//check for errors
// 	 							console.log(stock[1]);
// 	 							if((stock[1] <= 0) || (!Number.isInteger(Number(stock[1]))) || (stock[1] == ""))
// 	 							{
// 	 								//incorrect amount	
// 	 								console.log("bad stock amount");
// 	 							}
// 	 							else if(stock[2] == "")
// 	 							{
// 	 								//need buy date
// 	 							}
// 	 							else if(stock[3] == "")
// 	 			    			{
// 	 								//need sell date
// 	 			    			}
// 	 			    			else if(stock[3] < stock[2])
// 	 			    			{
// 	 			    				//buy date is after sell date
// 	 			    				console.log("buy date later");
// 	 			    			}	
// 	 			    			else if(stocks[2] > today)
// 	 			    			{
// 									//buy date after today
// 	 			    			}
// 								else if(today < stocks[3])
// 								{
// 									//sell date before today
// 								}
// 	 						}
	 						for(var i = 0; i < stocks.length; i++)
	 						{
	 							var stock = stocks[i].split(",");
	 							bulkAddStock(stock[0],stock[1],stock[2],stock[3]);
	 						}
	 					}
	 					reader.onerror = function(error)
	 					{
	 						
	 					}
	 					reader.readAsText(file)
	 					
				    }
					return false;
				});
				
				$('#bulkEditModalButton').click(
					function(e) {
						$('#bulkEditError').html("");
						$("#bulkEditModal").modal('show');
					}
				);
				
				$('#addHistoricalStock').click(
						function(e) {
							var tickerSymbol = $('#addHistoricalStockTicker').val();
							var numShares = $('#addHistoricalStockShares').val();
							var buyDate = $('#addHistoricalStockBuyDate').val();
							var sellDate = $('#addHistoricalStockSellDate').val();
							$('#addHistoricalStockError').html("");
							
							addHistoricalStock(tickerSymbol, numShares, buyDate, sellDate);		
						}
					);
				
				$('#deleteHistoricalStock').click(
						function(e) {
							deleteHistoricalStock();
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
				
				$('#addHistoricalStockModalButton').click(
						function(e) {
							$('#addHistoricalStockError').html("");
							$("#addHistoricalStockModal").modal('show');
						}
					);
				
				$('#logoutButton').click(
					function(e) {
						// * Send http request to server to overwrite session attribute.
						let HTTP = new XMLHttpRequest();
					    var d = new Date();
					    var n = d.getTime();
						const url = "https://localhost:8443/logout";
						HTTP.open("POST", url);
						HTTP.send();
						    
						HTTP.onreadystatechange = (e) => {
							if(HTTP.status == 200) {
							    // Successfully logged out user
							   	window.location.href = 'https://localhost:8443/index.jsp';
							}
						}
					}

				);
				
				$('#graphUnits').on('change', function(){
					if($('#graphUnits').val() == "weeks"){
						graphUnit = "W";
					}
					else{
						graphUnit = "D";
					}
					
					getPositions();
					getHistoricalPositions();
				});
				
				// Try to redirect user
				var username = '<%= session.getAttribute("username")%>';
				var null_username = '<%= session.getAttribute("username") == null %>';
				
				// Redirect user if they are not logged in
				if(username == "" || null_username == "true") {
					window.location.href = 'https://localhost:8443/index.jsp';
				}

			}
		);
	
	var currentPortfolioValueCounter = 0;
	
	// For each stock position, get portfolio value
	function getCurrentPortfolioValue() {
		var yesterdayPortfolioValue = 0;
		var currentPortfolioValue = 0;
		
		// Pulls data from 5 days in case dates have limited values
		var todayMinus5 = Date.parse(addDaysAndFormat(new Date(), -5))/1000;
		var today = Date.parse(addDaysAndFormat(new Date(), 0))/1000;
		
		var noPositionChecked = true;
		positions.forEach((value, key) => {
			if($("#cb-portfolio-" + key)[0] !== undefined && $("#cb-portfolio-" + key)[0].checked){
				noPositionChecked = false;
				const HTTP = new XMLHttpRequest();
	        	const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + key + "&resolution=D&from=" + todayMinus5 + "&to=" + today + "&token=" + finnhub_token;
	        	HTTP.open("GET", url);
	        	HTTP.send();
	
	        	HTTP.onreadystatechange = (e) => {
	        		if(HTTP.readyState == 4 && HTTP.status == 200){
	        			var response = JSON.parse(HTTP.responseText);
	        			var rawData = response.c;
	        			currentPortfolioValue += rawData[rawData.length-1]*value.shares;
	        			yesterdayPortfolioValue += rawData[rawData.length-2]*value.shares;
	        			currentPortfolioValueCounter++;
        				$("#currentPortfolioValue").html("$" + currentPortfolioValue.toFixed(2));
        				var percentChange = (currentPortfolioValue-yesterdayPortfolioValue)/yesterdayPortfolioValue*100;
        				if(percentChange >= 0){
        					$("#currentPortfolioValueChange").html("+" + percentChange.toFixed(2) + "%");
        					$("#redDownTriangle").css('display', 'none');
        					$("#greenUpTriangle").css('display', 'inline');
        				}
        				else{
        					$("#currentPortfolioValueChange").html(percentChange.toFixed(2) + "%");
        					$("#redDownTriangle").css('display', 'inline');
        					$("#greenUpTriangle").css('display', 'none');
        				}
        				
        				// for graph acceptance tests
        				$('#yesterdayPortfolioPoint').html(yesterdayPortfolioValue);
        				$('#todayPortfolioPoint').html(currentPortfolioValue);
	        		}
	        	}
			}
		});
		
		// Case we have no positions
		if(Array.from(positions.keys()).length === 0 || noPositionChecked) {
			$("#currentPortfolioValue").html("$0.00");
			$("#currentPortfolioValueChange").html("+0.00%");
			$("#redDownTriangle").css('display', 'none');
			$("#greenUpTriangle").css('display', 'inline');
		}
	}

	// Populate portfolio list
	function getPositions() {
        var username = '<%= session.getAttribute("username")%>'
        var HTTP = new XMLHttpRequest();
        var d = new Date();
        var n = d.getTime();
        const url = "https://localhost:8443/portfolio?username=" + username.toString() + "&t=" + n;
        HTTP.open("GET", url);
        HTTP.send();
        
        var prev_checked_positions = positions ? Array.from(positions.keys()) : [];
        if(prev_checked_positions.length > 0) {
        	stockHistoryLabels = []
        	stockHistory = []
        	let new_prev_checked = []
        	for(var x = 0; x < prev_checked_positions.length; x++) {
        		if($("#cb-portfolio-" + prev_checked_positions[x])[0] !== undefined && $("#cb-portfolio-" + prev_checked_positions[x])[0].checked) {
        			new_prev_checked.push(prev_checked_positions[x])
        		}
        	}
        	prev_checked_positions = new_prev_checked;
        }
        
        console.log(prev_checked_positions);
        $("#positions").html("");
        
        var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;display:flex;flex-wrap:wrap;justify-content:center;' id='select-all-portfolio'>" +
		"<button id=\"selectAllPortfolioButton\" style='margin:5px;' type=\"button\" onclick='stockChecked(\"Select-All\", { checked: true}, false)' class=\"btn btn-primary\">Select All</button>" +
		"<button id=\"deselectAllPortfolioButton\" style='margin:5px;' type=\"button\" onclick='stockChecked(\"Select-All\", { checked: false}, false)' class=\"btn btn-primary\">DeSelect All</button>" +
		"</div>";
		$("#positions").append(row);
		
     	// Reset Graph 
		config.data.labels= []
		config.data.datasets = []
        
        HTTP.onreadystatechange = async (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		//$("#positions").html("");
        		var getPositionResponse = JSON.parse(HTTP.responseText);
        		
        		// * Check if Total Portfolio Value is not already populated
        		if(stockHistoryLabels.indexOf("Total Portfolio Value") == -1){
	        		stockHistoryLabels.push("Total Portfolio Value");
					stockHistory.push([]);        			
        		}
        		
        		// * Get index of 
				var index = stockHistory.length-1;
        		
        		// * Init Portfolio Graph
        		var startDate = Date.parse($('#graphStartDate').val())/1000;
				var endDate = Date.parse($('#graphEndDate').val())/1000;
				
				// Grab dummy values to populate graph with correct number of points, initially all zero
				var new_HTTP = new XMLHttpRequest();
		        var url = "https://finnhub.io/api/v1/stock/candle?symbol=GOOGL&resolution=" + graphUnit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
		        new_HTTP.open("GET", url);
		        new_HTTP.send();
		        new_HTTP.onreadystatechange = (e) => {
		    		if(new_HTTP.readyState == 4 && new_HTTP.status == 200){
		    			var response = JSON.parse(new_HTTP.responseText);
		    			var rawData = response.c;
		    			var increment = DaysBetween($('#graphStartDate').val(), $('#graphEndDate').val())/rawData.length;
		    			stockHistory[index] = rawData;
		    			
		    			// * Set default data
						for(var i = 0; i < stockHistory[index].length; i++){
							stockHistory[index][i] = 0;
							config.data.labels.push(addDaysAndFormat($('#graphStartDate').val(), Math.round(i*increment)));
						}
						
						// For each position, add to total portfolio
						for(var i = 0; i < getPositionResponse.positions.length; i++){
		        			var tickerSymbol = getPositionResponse.positions[i].position;
		        			var shareCount = getPositionResponse.positions[i].share_count;
		        			var dateBought = getPositionResponse.positions[i].date_bought;
		        			var dateSold = getPositionResponse.positions[i].date_sold;
		        			var today = new Date();
		        			if(Date.parse(dateBought) <= today && today <= Date.parse(dateSold)){
		        				// if(!positions.has(tickerSymbol)) {
			        				var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;margin-top:5px;padding-right:10px;' id='r" + tickerSymbol + "'><div class='p-2 position-padding'><input type='checkbox' id='cb-portfolio-" + tickerSymbol + "' onclick='stockChecked(\"" + tickerSymbol + "\", this, false)'/></div><div class='position-padding'>" + tickerSymbol + 
			        				"</div><div><button type='button' class='btn' onclick=deleteStockModal('" + tickerSymbol + "')>X</button></div></div>";
			                		$("#positions").append(row);
			                		positions.set(tickerSymbol, new Position(tickerSymbol, shareCount, formatDate(dateBought), formatDate(dateSold)));
			                		if(prev_checked_positions.indexOf(tickerSymbol) !== -1) {
			                			stockChecked(tickerSymbol, {checked: true}, true)
			                		}
		        				// }
		        			}
		        		}

						if(prev_checked_positions.length === 0) {
							removeFromConfigDataSets("Total Portfolio Value");
			    			drawGraph("Total Portfolio Value", index);
							getCurrentPortfolioValue();
						}
		    		}
		        }
        		
        	}   
        }
    }
	
	// Populate historical trends
	function getHistoricalPositions() {
        var username = '<%= session.getAttribute("username")%>'
        let HTTP = new XMLHttpRequest();
        var d = new Date();
        var n = d.getTime();
        const url = "https://localhost:8443/historical?username=" + username.toString() + "&t=" + n;
        HTTP.open("GET", url);
        HTTP.send();
        
        var prev_checked_historical_positions = historicalPositions ? Array.from(historicalPositions.keys()) : [];
        if(prev_checked_historical_positions.length > 0) {
        	let new_prev_checked = []
        	for(var x = 0; x < prev_checked_historical_positions.length; x++) {
        		if($("#cb-historical-" + prev_checked_historical_positions[x])[0] !== undefined && $("#cb-historical-" + prev_checked_historical_positions[x])[0].checked) {
        			new_prev_checked.push(prev_checked_historical_positions[x])
        		}
        	}
        	prev_checked_historical_positions = new_prev_checked;
        }
        
        $("#historicalPositions").html("");
        
        var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;display:flex;flex-wrap:wrap;justify-content:center;' id='select-all-historical'>" +
		"<button id=\"selectAllHistoricalButton\" style='margin:5px;' type=\"button\" onclick='historicalStockChecked(\"Select-All\", { checked: true})' class=\"btn btn-primary\">Select All</button>" +
		"<button id=\"deselectAllHistoricalButton\" style='margin:5px;' type=\"button\" onclick='historicalStockChecked(\"Select-All\", { checked: false})' class=\"btn btn-primary\">DeSelect All</button>" +
		"</div>";
		$("#historicalPositions").append(row);
		
        HTTP.onreadystatechange = (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		//$("#historicalPositions").html("");
        		var response = JSON.parse(HTTP.responseText);
        		
        		
        		// * Append S&P Row
        		var s_p_ticker = "spy";
        		var s_p_row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;margin-top:5px;padding-right:10px;' id='r" + s_p_ticker + "'><div class='p-2 position-padding'><input type='checkbox' id='cb-historical-" + s_p_ticker + "' onclick='historicalStockChecked(\"" + s_p_ticker + "\", this)'/></div><div class='position-padding'>S&P" + 
				"</div><div><button type='button' class='btn' onclick=deleteHistoricalStockModal('" + s_p_ticker + "')>X</button></div></div>";
        		$("#historicalPositions").append(s_p_row);
        		
        		// * Init Portfolio Graph
        		var startDate = Date.parse($('#graphStartDate').val())/1000;
				var endDate = Date.parse($('#graphEndDate').val())/1000;
				
        		historicalPositions.set(s_p_ticker, new Position(s_p_ticker, 1, startDate, endDate));
        		historicalStockChecked(s_p_ticker, {checked: true});
        		
        		for(var i = 0; i < response.positions.length; i++){
        			var tickerSymbol = response.positions[i].position;
        			var shareCount = response.positions[i].share_count;
        			var dateBought = response.positions[i].date_bought;
        			var dateSold = response.positions[i].date_sold;
        			var today = new Date();
        			if(Date.parse(dateBought) <= today && today <= Date.parse(dateSold)){
        				var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;margin-top:5px;padding-right:10px;' id='r-historical-" + tickerSymbol + "'><div class='p-2 position-padding'><input type='checkbox' id='cb-historical-" + tickerSymbol + "' onclick='historicalStockChecked(\"" + tickerSymbol + "\", this)'/></div><div class='position-padding'>" + tickerSymbol + 
        				"</div><div><button type='button' class='btn' onclick=deleteHistoricalStockModal('" + tickerSymbol + "')>X</button></div></div>";
                		$("#historicalPositions").append(row);
                		historicalPositions.set(tickerSymbol, new Position(tickerSymbol, shareCount, formatDate(dateBought), formatDate(dateSold)));
                		if(prev_checked_historical_positions.indexOf(tickerSymbol) !== -1) {
                			historicalStockChecked(tickerSymbol, {checked: true})
                		}
        			}	
        		}
        	}   
        }
    }
	
	function deleteStockModal(tickerSymbol) {
		$("#modalTickerSymbol").html(tickerSymbol);
		$("#deleteStockModal").modal('show');
	}
	
	function deleteHistoricalStockModal(tickerSymbol) {
		$("#historicalModalTickerSymbol").html(tickerSymbol);
		$("#deleteHistoricalStockModal").modal('show');
	}
	
	function deleteStock() {
		var tickerSymbol = $("#modalTickerSymbol").text();
		var username = '<%= session.getAttribute("username")%>'
		
		const HTTP = new XMLHttpRequest();
       	const url = "https://localhost:8443/portfolio?username=" + username.toString() + "&position=" + tickerSymbol.toString();
       	HTTP.open("DELETE", url);
       	HTTP.send();
       
       	HTTP.onreadystatechange = (e) => {
       		if(HTTP.readyState == 4 && HTTP.status == 200){
       			$("#deleteStockModal").modal('hide');	
				$("#r" + tickerSymbol).remove();
				var index = stockHistoryLabels.indexOf(tickerSymbol);
        		if(index >= 0){
					removeFromConfigDataSets(tickerSymbol);
        			stockHistory.splice(index, 1);
        			stockHistoryLabels.splice(index, 1);
        		}
        		deleteFromTotalPortfolio(tickerSymbol, positions.get(tickerSymbol).shares);
        		positions.delete(tickerSymbol);
        		getCurrentPortfolioValue();
       		}   
       	}
	}
	
	function deleteHistoricalStock() {
		var tickerSymbol = $("#historicalModalTickerSymbol").text();
		var username = '<%= session.getAttribute("username")%>'
		
		const HTTP = new XMLHttpRequest();
       	const url = "https://localhost:8443/historical?username=" + username.toString() + "&position=" + tickerSymbol.toString();
       	HTTP.open("DELETE", url);
       	HTTP.send();
       
       	HTTP.onreadystatechange = (e) => {
       		if(HTTP.readyState == 4 && HTTP.status == 200){
       			$("#deleteHistoricalStockModal").modal('hide');	
				$("#r-historical-" + tickerSymbol).remove();
				var index = stockHistoryLabels.indexOf('Historical-' + tickerSymbol);
        		if(index >= 0){
        			removeFromConfigDataSets(tickerSymbol);
        			stockHistory.splice(index, 1);
        			stockHistoryLabels.splice(index, 1);
        		}
        		historicalPositions.delete(tickerSymbol);
       		}   
       	}
	}
	
	function addHistoricalStock(tickerSymbol, numShares, buyDate, sellDate){
		$('#addHistoricalStockErrorTS').html("");
		$('#addHistoricalStockErrorShares').html("");
		$('#addHistoricalStockErrorBuy').html("");
		$('#addHistoricalStockErrorSell').html("");
		
		const HTTP = new XMLHttpRequest();
	    const url = "https://finnhub.io/api/v1/quote?symbol=" + tickerSymbol.toString() + "&token=" + finnhub_token;
	    HTTP.open("GET", url);
	    HTTP.send();

    	HTTP.onreadystatechange = (e) => {
       		if(HTTP.readyState == 4 && HTTP.status == 200){
       			var error = false;
       			// invalid ticker symbol
       			if(HTTP.responseText.toString() == "{\"c\":0,\"h\":0,\"l\":0,\"o\":0,\"pc\":0,\"t\":0}"){
       				$('#addHistoricalStockErrorTS').html("Invalid ticker symbol.");
       				error = true;
       			}
       			else if(historicalPositions.has(tickerSymbol)){
       				$('#addHistoricalStockErrorTS').html("Portfolio already contains this stock.");
       				error = true;
       			}
    			// number of shares is left blank
    			if(numShares == ""){
    				$('#addHistoricalStockErrorShares').html("Please enter number of shares.");
    				error = true;
    			}
    			// number of shares is not an integer
    			else if(!Number.isInteger(Number(numShares))){
    				$('#addHistoricalStockErrorShares').html("Please enter a whole number of shares.");
    				error = true;
    			}
    			// number of shares is less than or equal to 0
    			else if(numShares <= 0){
    				$('#addHistoricalStockErrorShares').html("Number of shares must be greater than zero.");
    				error = true;
    			}
    			// both buy and sell date empty
    			if(!buyDate && !sellDate){
    				$('#addHistoricalStockErrorBuy').html("Purchase date is required.");
    				error = true;
    			}
    			// buy date is empty, sell date is not
    			else if(!buyDate){
    				$('#addHistoricalStockErrorBuy').html("Sold date without purchase date.");
    				error = true;
    			}
    			// sell date is empty, set to far date into the future to be shown "indefinitely"
    			else if(!sellDate){
    				sellDate = addDaysAndFormat(new Date(), 10000);
    			}
    			// sell date is before buy date
    			else if(sellDate < buyDate){
    				$('#addHistoricalStockErrorSell').html("Sold date is prior to purchase date.");
    				error = true;
    			}	
       				
       			// Get session attribute here 
       			var username = '<%= session.getAttribute("username")%>';

       			if(error == false){
       	            const HTTP = new XMLHttpRequest();
       	            const url = "https://localhost:8443/historical?username=" + username.toString() + "&position=" + tickerSymbol.toString() + "&share_count=" + 
       	            	numShares.toString() + "&date_bought=" + buyDate.toString() + "&date_sold=" + sellDate.toString();
       	            HTTP.open("POST", url);
       	            HTTP.send();
       	            
       	            HTTP.onreadystatechange = (e) => {
       	            	if(HTTP.readyState == 4 && HTTP.status == 200){
       	        			$("#addHistoricalStockModal").modal('hide');
       	        			var today = new Date();
       	            		if(Date.parse(buyDate.toString()) <= today && today <= Date.parse(sellDate.toString())){
       	            			var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;margin-top:5px;padding-right:10px;' id='r-historical-" + tickerSymbol + "'><div class='p-2 position-padding'><input type='checkbox' id='cb-historical-" + tickerSymbol + "' onclick='historicalStockChecked(\"" + tickerSymbol + "\", this)'/></div><div class='position-padding'>" + tickerSymbol + 
       	        				"</div><div><button type='button' class='btn' onclick=deleteHistoricalStockModal('" + tickerSymbol + "')>X</button></div></div>";
       	                		$("#historicalPositions").append(row);
       	                		historicalPositions.set(tickerSymbol, new Position(tickerSymbol, numShares, buyDate, sellDate));
       	            		}        		
       	            	}   
       	            }	
       			}	
       		}
    	}
	}
	
	function bulkAddStock(tickerSymbol, numShares, buyDate, sellDate){
		$('#bulkStockError').html("");
		
		const HTTP = new XMLHttpRequest();
	    const url = "https://finnhub.io/api/v1/quote?symbol=" + tickerSymbol.toString() + "&token=" + finnhub_token;
	    HTTP.open("GET", url);
	    HTTP.send();

    	HTTP.onreadystatechange = (e) => {
       		if(HTTP.readyState == 4 && HTTP.status == 200){
       			var error = false;
       			// invalid ticker symbol
       			if(HTTP.responseText.toString() == "{\"c\":0,\"h\":0,\"l\":0,\"o\":0,\"pc\":0,\"t\":0}"){
       				$('#bulkStockError').html("Invalid ticker symbol.");
       				error = true;
       			}
       			else if(positions.has(tickerSymbol)){
       				$('#bulkStockError').html("Portfolio already contains this stock.");
       				error = true;
       			}
    			// number of shares is left blank
    			if(numShares == ""){
    				$('#bulkStockError').html("Please enter number of shares.");
    				error = true;
    			}
    			// number of shares is not an integer
    			else if(!Number.isInteger(Number(numShares))){
    				$('#bulkStockError').html("Please enter a whole number of shares.");
    				error = true;
    			}
    			// number of shares is less than or equal to 0
    			else if(numShares <= 0){
    				$('#bulkStockError').html("Number of shares must be greater than zero.");
    				error = true;
    			}
    			// both buy and sell date empty
    			if(!buyDate && !sellDate){
    				$('#bulkStockError').html("Purchase date is required.");
    				error = true;
    			}
    			// buy date is empty, sell date is not
    			else if(!buyDate){
    				$('#bulkStockError').html("Sold date without purchase date.");
    				error = true;
    			}
    			// sell date is empty, set to far date into the future to be shown "indefinitely"
    			else if(!sellDate){
    				sellDate = addDaysAndFormat(new Date(), 10000);
    			}
    			// sell date is before buy date
    			else if(sellDate < buyDate){
    				$('#bulkStockError').html("Sold date is prior to purchase date.");
    				error = true;
    			}	
       				

       			// Get session attribute here 
       			var username = '<%= session.getAttribute("username")%>';
       			
       			if(error == false){
       	            const HTTP = new XMLHttpRequest();
       	            const url = "https://localhost:8443/portfolio?username=" + username.toString() + "&position=" + tickerSymbol.toString() + "&share_count=" + 
       	            	numShares.toString() + "&date_bought=" + buyDate.toString() + "&date_sold=" + sellDate.toString();
       	            HTTP.open("POST", url);
       	            HTTP.send();
       	            
       	            HTTP.onreadystatechange = (e) => {
       	            	if(HTTP.readyState == 4 && HTTP.status == 200){
       	        			$("#bulkEditModal").modal('hide');
       	        			var today = new Date();
       	            		if(Date.parse(buyDate.toString()) <= today && today <= Date.parse(sellDate.toString())){
       	            			var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;margin-top:5px;padding-right:10px;' id='r" + tickerSymbol + "'><div class='col-sm-2 position-padding'><input type='checkbox' id='cb-portfolio-" + tickerSymbol + "' onclick='stockChecked(\"" + tickerSymbol + "\", this)'/></div><div class='col-sm-8 position-padding'>" + tickerSymbol + 
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
    			// sell date is empty, set to far date into the future to be shown "indefinitely"
    			else if(!sellDate){
    				sellDate = addDaysAndFormat(new Date(), 10000);
    			}
    			// sell date is before buy date
    			else if(sellDate < buyDate){
    				$('#addStockErrorSell').html("Sold date is prior to purchase date.");
    				error = true;
    			}	
       				

       			// Get session attribute here 
       			var username = '<%= session.getAttribute("username")%>';
       			
       			if(error == false){
       	            const HTTP = new XMLHttpRequest();
       	            const url = "https://localhost:8443/portfolio?username=" + username.toString() + "&position=" + tickerSymbol.toString() + "&share_count=" + 
       	            	numShares.toString() + "&date_bought=" + buyDate.toString() + "&date_sold=" + sellDate.toString();
       	            HTTP.open("POST", url);
       	            HTTP.send();
       	            
       	            HTTP.onreadystatechange = (e) => {
       	            	if(HTTP.readyState == 4 && HTTP.status == 200){
       	        			$("#addStockModal").modal('hide');
       	        			var today = new Date();
       	            		if(Date.parse(buyDate.toString()) <= today && today <= Date.parse(sellDate.toString())){
       	            			var row = "<div class='row' style='border-width:thin;border:solid;border-radius:5px;margin-top:5px;padding-right:10px;' id='r" + tickerSymbol + "'><div class='p-2 position-padding'><input type='checkbox' id='cb-portfolio-" + tickerSymbol + "' onclick='stockChecked(\"" + tickerSymbol + "\", this, false)'/></div><div class='position-padding'>" + tickerSymbol + 
       	        				"</div><div><button type='button' class='btn' onclick=deleteStockModal('" + tickerSymbol + "')>X</button></div></div>";
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
		
		// for graph acceptance tests
		recalcNumGraphPoints();
	}
	
	function getStockHistory(){
		return stockHistory;
	}
	
	function populateStockHistory(tickerSymbol, unit) {
		var startDate = Date.parse($('#graphStartDate').val())/1000;
		var endDate = Date.parse($('#graphEndDate').val())/1000;
		
		const HTTP = new XMLHttpRequest();
        const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + tickerSymbol + "&resolution=" + unit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
        HTTP.open("GET", url);
        HTTP.send();
        
        HTTP.onreadystatechange = (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		var response = JSON.parse(HTTP.responseText);
        		var rawData = response.c;
        		stockHistory.push([]);
        		var index = stockHistory.length-1;
        		stockHistoryLabels.push(tickerSymbol);
        		for(var i = 0; i < rawData.length; i++){
        			stockHistory[index].push(rawData[i]*positions.get(tickerSymbol).shares);
        		}
        		drawGraph(tickerSymbol, index);
        	}
        } 
    }
	
	function populateHistoricalStockHistory(tickerSymbol, unit) {
		var startDate = Date.parse($('#graphStartDate').val())/1000;
		var endDate = Date.parse($('#graphEndDate').val())/1000;
		
		const HTTP = new XMLHttpRequest();
        const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + tickerSymbol + "&resolution=" + unit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
        HTTP.open("GET", url);
        HTTP.send();
        
        HTTP.onreadystatechange = (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		var response = JSON.parse(HTTP.responseText);
        		var rawData = response.c;
        		stockHistory.push([]);
        		var index = stockHistory.length-1;
        		stockHistoryLabels.push('Historical-' + tickerSymbol);
        		for(var i = 0; i < rawData.length; i++){
        			stockHistory[index].push(rawData[i]*historicalPositions.get(tickerSymbol).shares);
        		}
        		drawGraph(tickerSymbol, index);
        	}
        } 
    }
	
	function populateStockHistoryChangeUnit(tickerSymbol, unit, iteration) {
		var startDate = Date.parse($('#graphStartDate').val())/1000;
		var endDate = Date.parse($('#graphEndDate').val())/1000;
		
		const HTTP = new XMLHttpRequest();
        const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + tickerSymbol + "&resolution=" + unit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
        HTTP.open("GET", url);
        HTTP.send();
        
        HTTP.onreadystatechange = (e) => {
        	if(HTTP.readyState == 4 && HTTP.status == 200){
        		var response = JSON.parse(HTTP.responseText);
        		var rawData = response.c;
        		var increment = DaysBetween($('#graphStartDate').val(), $('#graphEndDate').val())/rawData.length;
        		stockHistory.push([]);
        		var index = stockHistory.length-1;
        		for(var i = 0; i < rawData.length; i++){
        			if(iteration == 0){
							config.data.labels.push(addDaysAndFormat($('#graphStartDate').val(), i*increment));
        			}
        			stockHistory[index].push(rawData[i]*positions.get(tickerSymbol).shares);
        		}
        		
        		drawGraph(tickerSymbol, index);
        	}
        } 
    }

	// Helper function to iterate over config.data.datasets and remove the data with label == tickerSymbol
	function removeFromConfigDataSets(tickerSymbol) {
		var remove_index = -1;
		for(var i = 0; i < config.data.datasets.length; i++) {
			if(config.data.datasets[i].label == tickerSymbol) {
				remove_index = i;
			}
		}
		if(remove_index !== -1) {
			config.data.datasets.splice(remove_index, 1); 
			window.myLine.update();
			recalcNumGraphPoints();
		}
	}
	
    function stockChecked(tickerSymbol, checkBox, fromGetPositions){
    	// if unchecked, remove from stock history and redraw
    	if(!checkBox.checked){
    		if(tickerSymbol === "Select-All") {
    			positions.forEach((pos, key) => {
    				if(key !== "Total Portfolio Value" && key.indexOf("Historical-") === -1) {
    					$("#cb-portfolio-" + key)[0].checked = false;
	    		    	deleteFromTotalPortfolio(key, positions.get(key).shares);
	    		    	earliestStartDates.delete(key);
    				}
    			});
    			
    			// * All are unchecked, we can set the start date back to 3 months
    			zooming_graph_lock = true;
    			$('#graphStartDate').datepicker("update", addDaysAndFormat(new Date(), -92));
    			zooming_graph_lock = false;
    			
    		} else {
	    		deleteFromTotalPortfolio(tickerSymbol, positions.get(tickerSymbol).shares);
	    		earliestStartDates.delete(tickerSymbol);
	    		
	    		var earliest_position_bought_date = new Date();
				earliestStartDates.forEach((pos, key) => {
					if(new Date(earliestStartDates.get(key).buyDate) < earliest_position_bought_date) {
	    				earliest_position_bought_date = new Date(earliestStartDates.get(key).buyDate) ;
	    			}
				});
				
				zooming_graph_lock = true;
				$('#graphStartDate').datepicker("update", addDaysAndFormat(earliest_position_bought_date, 0));
		    	zooming_graph_lock = false;
    		}
    	}
    	else {
    		if(tickerSymbol === "Select-All") {
    			positions.forEach((pos, key) => {
    				if(key !== "Total Portfolio Value" && key.indexOf("Historical-") === -1) {
    					$("#cb-portfolio-" + key)[0].checked = true;
    					earliestStartDates.set(key, positions.get(key));
    				}
    			});
    			
    			if(!fromGetPositions) {
    				var earliest_position_bought_date = new Date();
        			earliestStartDates.forEach((pos, key) => {
        				if(new Date(earliestStartDates.get(key).buyDate) < earliest_position_bought_date) {
            				earliest_position_bought_date = new Date(earliestStartDates.get(key).buyDate);
            			}
        			});
        			
        			zooming_graph_lock = true;
        			$('#graphStartDate').datepicker("update", addDaysAndFormat(earliest_position_bought_date, 0));
        	    	zooming_graph_lock = false;
    			}
    			
    			positions.forEach((pos, key) => {
    				if(key !== "Total Portfolio Value" && key.indexOf("Historical-") === -1) {
    					$("#cb-portfolio-" + key)[0].checked = true;
		    			addToTotalPortfolio(key, positions.get(key).shares);
    				}
    			});
    		} else {
				$("#cb-portfolio-" + tickerSymbol)[0].checked = true;
				earliestStartDates.set(tickerSymbol, positions.get(tickerSymbol));
				
				if(!fromGetPositions) {
					var earliest_position_bought_date = new Date();
					earliestStartDates.forEach((pos, key) => {
						if(new Date(earliestStartDates.get(key).buyDate) < earliest_position_bought_date) {
		    				earliest_position_bought_date = new Date(earliestStartDates.get(key).buyDate);
		    			}
					});
					
					zooming_graph_lock = true;
					$('#graphStartDate').datepicker("update", addDaysAndFormat(earliest_position_bought_date, 0));
			    	zooming_graph_lock = false;
				}
		    	
				addToTotalPortfolio(tickerSymbol, positions.get(tickerSymbol).shares);
	    	}
    		
    		
    	}
    }
    
    function historicalStockChecked(tickerSymbol, checkBox){
    	// if unchecked, remove from stock history and redraw
    	if(!checkBox.checked){
    		if(tickerSymbol === "Select-All") {
    			historicalPositions.forEach((pos, key) => {
    				if(key !== "Total Portfolio Value") {
    					var i = stockHistoryLabels.indexOf('Historical-' + key);
    					removeFromConfigDataSets(key);
    					if(i !== -1) {
	    		    		stockHistory.splice(i, 1);
	    		    		stockHistoryLabels.splice(i, 1);
	    		    		$("#cb-historical-" + key)[0].checked = false;    						
    					}
    				}
    			});
    		} else {
	    		var index = stockHistoryLabels.indexOf('Historical-' + tickerSymbol);
	    		removeFromConfigDataSets(tickerSymbol);
	    		stockHistory.splice(index, 1);
	    		stockHistoryLabels.splice(index, 1);    			
    		}
    	}
    	else {
    		if(tickerSymbol === "Select-All") {
    			historicalPositions.forEach((pos, key) => {
    				if(key !== "Total Portfolio Value") {
    					var i = stockHistoryLabels.indexOf('Historical-' + key);
    					if(i == -1){
    						$("#cb-historical-" + key)[0].checked = true;
	    					populateHistoricalStockHistory(key, graphUnit); 
    					}
    				}
    			});
    		} else {
    			$("#cb-historical-" + tickerSymbol)[0].checked = true;
	    		populateHistoricalStockHistory(tickerSymbol, graphUnit);
    		}
    	}
    }
    
    function addToTotalPortfolio(tickerSymbol, numShares){
    	var parsed_start_date = $('#graphStartDate').val() ? $('#graphStartDate').val() : addDaysAndFormat(new Date(), -92);
    	var parsed_end_date = $('#graphEndDate').val() ? $('#graphEndDate').val() : addDaysAndFormat(new Date(), 0);
    	var startDate = Date.parse(parsed_start_date)/1000;
		var endDate = Date.parse(parsed_end_date)/1000;
		
		var index = stockHistoryLabels.indexOf("Total Portfolio Value");
		
		const HTTP = new XMLHttpRequest();
    	const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + tickerSymbol + "&resolution=" + graphUnit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
    	HTTP.open("GET", url);
    	HTTP.send();

    	
    	HTTP.onreadystatechange = (e) => {
    		if(HTTP.readyState == 4 && HTTP.status == 200){
    			var response = JSON.parse(HTTP.responseText);
    			var rawData = response.c;
    			var temp_length = rawData ? rawData.length : 0;
    			let result_arr = [];
    			
    			var increment = DaysBetween($('#graphStartDate').val(), $('#graphEndDate').val())/rawData.length;
    			config.data.labels = []
    			
    			// * Set default data
				for(var i = 0; i < temp_length; i++){
					config.data.labels.push(addDaysAndFormat($('#graphStartDate').val(), Math.round(i*increment)));
				}
    			
    			for(var i = 0; i < temp_length; i++){	
    				if(stockHistory[index][i] === undefined) {
    					stockHistory[index].push(rawData[i]*numShares);   
    				} else {
	    				stockHistory[index][i] += rawData[i]*numShares;    					
    				}
    				
    			}
    			
    			// Try to remove Total Portfolio Value from graph and redraw
    			removeFromConfigDataSets("Total Portfolio Value");
    			drawGraph("Total Portfolio Value", index);
    			getCurrentPortfolioValue();
    		}
    	}
    }
    
    function deleteFromTotalPortfolio(tickerSymbol, numShares){
    	var startDate = Date.parse($('#graphStartDate').val())/1000;
		var endDate = Date.parse($('#graphEndDate').val())/1000;
		
		const HTTP = new XMLHttpRequest();
    	const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + tickerSymbol + "&resolution=" + graphUnit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
    	HTTP.open("GET", url);
    	HTTP.send();

    	HTTP.onreadystatechange = (e) => {
    		if(HTTP.readyState == 4 && HTTP.status == 200){
    			var response = JSON.parse(HTTP.responseText);
    			var rawData = response.c;
    			
    			var increment = DaysBetween($('#graphStartDate').val(), $('#graphEndDate').val())/rawData.length;
    			config.data.labels = []
    			
    			// * Set default data
				for(var i = 0; i < rawData.length; i++){
					config.data.labels.push(addDaysAndFormat($('#graphStartDate').val(), Math.round(i*increment)));
				}
    			
    			var index = stockHistoryLabels.indexOf("Total Portfolio Value");
    			removeFromConfigDataSets("Total Portfolio Value");
    			var newPortfolioData = stockHistory[index];
    			
    			for(var i = 0; i < rawData.length; i++){	
    				if(newPortfolioData[i] - rawData[i]*numShares < 0.1 && newPortfolioData[i] - rawData[i]*numShares > -0.1) {
    					newPortfolioData[i] = 0;
    				} else {
	    				newPortfolioData[i] -= rawData[i]*numShares;    					
    				}
    			}
    			stockHistory[index] = newPortfolioData;
    			removeFromConfigDataSets("Total Portfolio Value");
    			drawGraph("Total Portfolio Value", index);
    			getCurrentPortfolioValue();
    		}
    	}
    }
    
    function getPortfolioValueHistory(unit, index){
    	var startDate = Date.parse($('#graphStartDate').val())/1000;
		var endDate = Date.parse($('#graphEndDate').val())/1000;
		counter = 0;
		if(positions.size == 0){
			var index = stockHistoryLabels.indexOf("Total Portfolio Value");
			drawGraph("Total Portfolio Value", index);
		}

		for(let [key, value] of positions){
			var checked = $("[id=cb-portfolio-"+key+"]:checked").length;
				const HTTP = new XMLHttpRequest();
        		const url = "https://finnhub.io/api/v1/stock/candle?symbol=" + key + "&resolution=" + unit + "&from=" + startDate + "&to=" + endDate + "&token=" + finnhub_token;
        		HTTP.open("GET", url);
        		HTTP.send();
        		//alert(stockHistory[stockHistory.length-1]);
        		HTTP.onreadystatechange = (e) => {
        			if(HTTP.readyState == 4 && HTTP.status == 200){
        				var response = JSON.parse(HTTP.responseText);
        				var rawData = response.c;
        				var increment = DaysBetween($('#graphStartDate').val(), $('#graphEndDate').val())/rawData.length;
        				for(var i = 0; i < stockHistory[index].length; i++){	
        					stockHistory[index][i] += rawData[i]*value.shares;
        				}
        			//alert(stockHistory[stockHistory.length-1]);
        				portfolioHistoryComplete(index);
        			}
        		}
		}
		
    }
    
    var counter = 0;
    function portfolioHistoryComplete(index){
    	counter++;
    	var numChecked = $("[id^=cb-portfolio]:checked").length;
    	if(counter == numChecked){	
    		drawGraph("Total Portfolio Value", index);
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
    	$('#graphStartDate').datepicker("update", addDaysAndFormat(new Date(), -92));
		$('#graphEndDate').datepicker("update", addDaysAndFormat(new Date(), 0));
    }
    
    function validateGraphDates(type) {
    	if($('#graphEndDate').val() != "" && new Date($('#graphEndDate').val()) < new Date($('#graphStartDate').val())){
    		$('#graphDateError').html('End date before start date');
    		if($('#graphDateError').text() === "") {
	    		$('#graphEndDate').datepicker("update", graphEndDate);	    			
    		}
    	}
    	else {
    		if(new Date($('#graphStartDate').val()).getTime() === new Date($('#graphEndDate').val()).getTime()) {
    			zooming_graph_lock = true;
    			$('#graphStartDate').datepicker("update", graphStartDate);
    			zooming_graph_lock = false;
    		} else {
	    		graphEndDate = $('#graphEndDate').val();
	    		$('#graphDateError').html('');
	    		if(!zooming_graph_lock) { 
		    		getPositions();  
		    		getHistoricalPositions();
	    		}    			
    		}
    	}
    }
    
    const sleep = (milliseconds) => {
    	return new Promise(resolve => setTimeout(resolve, milliseconds));
    }
    
    var graphUnit = "D";
    
    
	
	
	</script>
	<script>
		var lab = []
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
	<script>
	function recalcNumGraphPoints(){
		var graphPoints = 0;
		for(var i = 0; i < config.data.datasets.length; i++){
			graphPoints+=config.data.datasets[i].data.length;
		}
		$('#graphPoints').html(graphPoints);
	}
	
	</script>
	


</body>
</html>
