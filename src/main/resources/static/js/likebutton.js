let numeroLikes;
let getUrl = window.location.href + '/like';

console.log(getUrl)
axios.get(getUrl)
	.then(function(response) {
		numeroLikes = response['data']['numeroLike'];
		console.log(numeroLikes)
		document.querySelector('#like').text = numeroLikes;
	})
	.catch(function(error) {
		console.log(error);
	});

function addLike() {
	numeroLikes++;
	axios.post(getUrl, {
		numeroLike: numeroLikes
	})
		.then(function(response) {
			console.log(numeroLikes)
			document.querySelector('#like').text = numeroLikes;
		})
		.catch(function(error) {
			console.log(error);
		});
}
// ඞ