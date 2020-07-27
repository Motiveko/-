


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
    var url2 = "http://localhost:8090/restaurants";
    return new Promise(function(resolve, reject) { 
	fetch(url2).then(res=>{
	    //.json도 비동기로 처리해야한다
	    return res.json();
	}).then( response =>{
	    resolve(response);
	})
    });
}
getData().then(function(restaurants){


    console.log(restaurants);
   
    const element = document.getElementById("app");
    element.innerHTML=`
	${restaurants.map(restaurant => `
        	<p>
        	${restaurant.id}
        	${restaurant.name}
        	${restaurant.address}		
        	</p>
	
	`).join('')}
    `;
})
