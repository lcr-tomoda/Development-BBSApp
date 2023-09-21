window.onload = function() {
	const table = document.getElementById("post_list");
	const trs = table.rows;
	console.log(trs);

	for (i = 1; i < trs.length; i++) {
		trs[i].addEventListener("click", function() {
			const id = this.querySelector(".id").innerHTML;
			window.location.href = "/post/" + id;
		});
	}
}
