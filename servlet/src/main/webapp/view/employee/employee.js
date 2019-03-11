function doSubmit(action) {
	if (action == 'add') {
		document.forms[0].method = "POST";
		$('input[name="action"]').val('add');
	} else if (action == 'back') {
		document.forms[0].method = "POST";
		$('input[name="action"]').val('list');
	} else if (action == 'save') {
		document.forms[0].method = "POST";
		$('input[name="action"]').val('save');
	} else if (action == 'update') {
		document.forms[0].method = "POST";
		$('input[name="action"]').val('update');
	}

	document.forms[0].submit();
}

function doUpdate(id) {
	document.forms[0].method = "POST";
	$('input[name="action"]').val('getId');
	$('input[name="id"]').val(id);
	document.forms[0].submit();
}

function doDelete(id) {
	document.forms[0].method = "POST";
	$('input[name="action"]').val('delete');
	$('input[name="id"]').val(id);
	document.forms[0].submit();
}