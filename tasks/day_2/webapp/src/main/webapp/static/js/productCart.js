var UPDATE_ORDER_ITEM_SERVLET = ctx + '/order/update_quantity';
const PRODUCT_COUNTER_CLASS='.productCounter';

hangOnCounterChangeEvent(document.querySelectorAll(PRODUCT_COUNTER_CLASS));

function hangOnCounterChangeEvent(elements){
	console.log('method # hangOnCounterChangeEvent');
	console.log(UPDATE_ORDER_ITEM_SERVLET);
	elements.forEach(element => {
		element.addEventListener('change', (event) => {
			let clickedOrderItem = event.target;
			parameterItems = prepareUpdatePackage(extractIdPart (clickedOrderItem.id), clickedOrderItem.value);
			sendRequest(UPDATE_ORDER_ITEM_SERVLET, 'POST', parameterItems);
		});
	});
}

function prepareParameterUrl(parameterItems){
	console.log('method # hangOnCounterChangeEvent');
	let resultParameterPart='';
	parameterItems.forEach((element) => {
		resultParameterPart += element.name + '=' + element.value + '&';
	});
	return  resultParameterPart;
}

function prepareUpdatePackage(productId, productCount){
	console.log('method # hangOnCounterChangeEvent');
	let parameterItems = new Array();
            let productIdParameter = {name: 'productId', value: productId };
            productCountParameter = {name: 'productCount', value: productCount };
            parameterItems.push(productIdParameter);
            parameterItems.push(productCountParameter);
            return parameterItems;
}

async function sendRequest(address, method, parameters){
console.log(address);
	let requestObject;
	if(typeof XMLHttpRequest != 'undefined'){
		requestObject = new XMLHttpRequest();
	}
	requestObject.open(method, address , true);
	if(method == 'POST'){
		requestObject.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	}
	let parameterUrl = prepareParameterUrl(parameters);
	await requestObject.send(parameterUrl);
}

function extractIdPart(str){
	let lastIndexOfBottomLine = str.lastIndexOf('_');
	return str.substring(lastIndexOfBottomLine + 1, str.lenght);
}