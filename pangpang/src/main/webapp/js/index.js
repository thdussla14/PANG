get();
function get(){
	
	$.ajax({
		url : "/pangpang/crawling",
		method : "get",
		success : ( r ) => {
			
			let html = `
						<div class="temp"> ${r.current} </div>
						<div class="summary"> ${r.summary} </div>
						`
			document.querySelector('.temp_area').innerHTML = html ;
			
		}	
	})
}


const ctx = document.getElementById('myChart');



let date = [];
let count = [];
chartPrint();
function chartPrint(){
	
	$.ajax({
		url : "/pangpang/chart",
		method : "get",
		async: false,
		success : ( r ) => {
			
			r.forEach( (o) =>{
				date.push(o.date);
				count.push(o.count);
			})
			
		}
	})
}
 
 
new Chart(ctx, {
    	type: 'bar',
    	data: {
	      	labels: date,
	      	datasets: [{
	        	label: '일별 주문량',
	        	data: count,
	        	borderWidth: 1
	      	}]
    	},
    	options: {
      	scales: {
        	y: {
          		beginAtZero: true
        		}
      		}
    	}
	}); 