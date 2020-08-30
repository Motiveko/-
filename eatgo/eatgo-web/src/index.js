


//(async() =>{
//
//    const url = "http://localhost:8090/restaurants";
//    const response = await fetch(url);
//    console.log(response);
//    const restaurants =await response.json();
//
//    const element = document.getElementById("app");
//    
//    console.log(restaurants);
//    element.innerHTML=`
//	${restaurants.map(restaurant => `
//        	<p>
//        	${restaurant.id}
//        	${restaurant.name}
//        	${restaurant.address}		
//        	</p>
//	
//	`).join('')}
//    `;
//    
//})();


 function getData(callback){
     var url2 = "http://localhost:8090/users";
     return new Promise(function(resolve, reject) { 
	 	fetch(url2).then(res=>{
	 	    //.json도 비동기로 처리해야한다
	 	    return res.json();
	 	}).then( response =>{
	 	    resolve(response);
	 	})
     });
 }
// getData().then(function(restaurants){


//     console.log(restaurants);
   
//     const element = document.getElementById("app");
//     element.innerHTML=`
// 	${restaurants.map(restaurant => `
//         	<p>
//         	${restaurant.id}
//         	${restaurant.name}
//         	${restaurant.address}		
//         	</p>
	
// 	`).join('')}
//     `;
// })


// 여기서부터 내가 코딩

$(document).ready(function(){
	console.log('Im ready!');

	$(".submitBtn").on('click',function(){

		// formdata to JSON
		var formArray = $("#registerForm").serializeArray();
		var data = {};
		$.each(formArray, function(i,v){
			// data[`\"${v.name}\"`] = v.value;
			data[v.name] = v.value;
		})
		// 씨바 이렇게 stringify해서 "key":"value"로 넘겨줘야합니다 아니면 못알아먹네요씨바
		var string = JSON.stringify(data);
		console.log(string);

		$.ajax({

			url: "http://localhost:8090/users",
			method: "POST",
			contentType: 'application/json',
			dataType: "json",
			data: string,
			success: function(res){
				alert("user register successed");
				console.log(res);
			},
			error: function(){
				alert("뭔가 문제생김");
			}
		})
	})
	

})



