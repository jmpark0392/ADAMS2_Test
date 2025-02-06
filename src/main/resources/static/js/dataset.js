var statusName = "rowStatus";
function DataSet() {
	
	var ds_header;
	var ds_body = [];
	var org_ds_body = [];
	var gridId = "";
	var key = "";
	
	this.setHeader = function (header) {
		this.ds_header = header;
	}
	
	this.setGrid = function (id, key) {
		this.gridId = id;
		this.key = key;
	}
	
	this.drawGrid = function (data, afterFn) {
		if (this.ds_header == undefined || this.ds_header.length <= 0) {
			return false;
		}
		
		if (this.gridId == undefined || this.gridId == "") {
			return false;
		}
		
		this.ds_body = [];
		this.org_ds_body = [];
		
		$("#"+this.gridId).empty();

		var tmp_thead = "<thead><tr>";
		for (idx in this.ds_header) {
			if (this.ds_header[idx].visible == true) {
				tmp_thead += "<th field='"+this.ds_header[idx].field+"' style='width:"+this.ds_header[idx].width+"px; text-align:center;'>"+this.ds_header[idx].name+"</th>";
			}
		}
		tmp_thead += "</tr></thead>";
		
		$("#"+this.gridId).append(tmp_thead);

		var tmp_tbody = "<tbody>";
		for (i in data) {
			var rowData = data[i];
			var currBody = {};
			var orgBody = {};
			var tmp_tr = "<tr>";
			for (j in this.ds_header) {
				if (this.ds_header[j].visible == true) {
					var cellValue = String(eval("rowData."+this.ds_header[j].field));
					var editable = "";
					//sheet.setValue(i, j, cellValue);
					if (this.ds_header[j].type == "text") {
						if (this.ds_header[j].editable == false) {
							editable = "readonly";
						} else {
							editable = "";
						}
						tmp_tr += "<td><input type='text' name='"+this.ds_header[j].field+"' value='"+cellValue+"' "+editable+"/></td>";
					} else if (this.ds_header[j].type == "check") {
						var checked="";
						if (cellValue == "Y") {
							checked = "checked='checked'";
						}
						if (this.ds_header[j].editable == false) {
							editable = "disabled";
						}
						tmp_tr += "<td>";
						tmp_tr += "<input type='checkbox' name='"+this.ds_header[j].field+"' "+checked+" "+editable+" value='Y'/>";
						tmp_tr += "<input type='hidden' name='_"+this.ds_header[j].field+"' value='N'/>";
						tmp_tr += "</td>";
					} else {
						if (this.ds_header[j].field == "rowStatus") {
							var circle_color = "";
							if (cellValue == "A") {
								circle_color = "blue";
							} else if (cellValue == "M") {
								circle_color = "yellow";
							} else if (cellValue == "D") {
								circle_color = "red";
							} else {
								circle_color = "white";
							}
							tmp_tr += "<td>"
							tmp_tr += "<span data-index='0' data-value='1' class='gl-active'>"
							tmp_tr += "<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='currentColor' class='icon gl-star-full text-"+circle_color+" icon-3' style='pointer-events: none;'>"
							tmp_tr += "<path d='M7 3.34a10 10 0 1 1 -4.995 8.984l-.005 -.324l.005 -.324a10 10 0 0 1 4.995 -8.336z'>"
							tmp_tr += "</path></svg></span>"
							tmp_tr += "</td>";
							//tmp_tr += "<td>"+cellValue+"</td>";
						} else {
							tmp_tr += "<td>"+cellValue+"</td>";
						}
					}
				}
				currBody[this.ds_header[j].field] = cellValue;
				orgBody[this.ds_header[j].field] = cellValue;
			}
			tmp_tr+="</tr>";
			tmp_tbody+=tmp_tr;

			this.ds_body.push(currBody);
			this.org_ds_body.push(orgBody);
		}
		tmp_tbody+="</tbody>";
		$("#"+this.gridId).append(tmp_tbody);
		
		$(document).on("change", "input[type=text],input[type=checkbox]", function () {
			eval(afterFn+"()");
		});
	}
	
	this.gridSerialize = function () {
		if (this.ds_body.length>0) {
			return JSON.stringify(this.ds_body);	
		} else {
			return "";
		}
	}
	
	this.getCIndex = function(name) {
		var columns = $("#"+this.gridId).find("th");
		for (var col=0; col<columns.length; col++) {
			if (columns.eq(col).html()==name) {
				return col;
			}
		}
	}
	
	this.setStatus = function() {
		for (rowIdx in this.ds_body) {
			var currBody = this.ds_body[rowIdx];
			if (currBody[this.key] == null || currBody[this.key] === "") {
				currBody[statusName] = "A";
			}
		}
		for (rowIdx in this.org_ds_body) {
			var orgBody = this.org_ds_body[rowIdx];
			var currBody = this.getBodyMatchKey(orgBody[this.key]);
			
			if (currBody == "") {
				currBody = orgBody;
				currBody[statusName] = "D";
				this.ds_body.push(currBody);
			} else {
				if (this.isModified(orgBody, currBody)) {
					currBody[statusName] = "M";
				} else {
					currBody[statusName] = "N";
				}
			}
		}
	}
	
	this.getBodyMatchKey = function(value) {
		for (i in this.ds_body) {
			var tempBody = this.ds_body[i];
			if (tempBody[this.key] == value) {
				return tempBody;
			} else {
				continue;
			}
		}
		return "";
	}
	
	this.isModified = function(orgBody, currBody) {
		for (colIdx in this.ds_header) {
			if (this.ds_header[colIdx].field == statusName) {
				continue;
			} else {
				if (orgBody[this.ds_header[colIdx].field] != currBody[this.ds_header[colIdx].field]) {
					console.log(this.ds_header[colIdx].field+"="+orgBody[this.ds_header[colIdx].field]+"||"+currBody[this.ds_header[colIdx].field]);
					return true;
				} else {
					continue;
				}
			}
		}
		return false;
	}
	
	this.applyStatus = function(idxStatus) {
		var rows = $("#"+this.gridId).find("tbody").children("tr");
		this.ds_body = [];
		
		for (var rowIdx=0; rowIdx<rows.length; rowIdx++) {
		    var body = {};
		    for (col in this.ds_header) {
				var name = this.ds_header[col].name;
				var field = this.ds_header[col].field;
				var type = this.ds_header[col].type;
				var tmpObj = rows.eq(rowIdx).find("input[name="+field+"]");
		        if (field == statusName) {
					continue;
				} else {
					if (type == "text") {
						body[field] = tmpObj.val();
					} else if (type == "check") {
						if (tmpObj.is(':checked')) {
							body[field] = "Y";
						} else {
							body[field] = "N";
						}
					}
				}
		    }
			this.ds_body.push(body);
		}

		this.setStatus();

		for (rowIdx in this.ds_body) {
			var status = this.ds_body[rowIdx]["rowStatus"];
			var tmpTD = rows.eq(rowIdx).find("td").eq(idxStatus);
			var circle_color = "";
			var tmp_tr = "";
			
			tmpTD.empty();
			
			if (status == "A") {
				circle_color = "blue";
			} else if (status == "M") {
				circle_color = "yellow";
			} else if (status == "D") {
				circle_color = "red";
			} else if (status == "N") {
				circle_color = "white";
			}

			tmp_tr += "<span data-index='0' data-value='1' class='gl-active'>"
			tmp_tr += "<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='currentColor' class='icon gl-star-full text-"+circle_color+" icon-3' style='pointer-events: none;'>"
			tmp_tr += "<path d='M7 3.34a10 10 0 1 1 -4.995 8.984l-.005 -.324l.005 -.324a10 10 0 0 1 4.995 -8.336z'>"
			tmp_tr += "</path></svg></span>"

			tmpTD.append(tmp_tr);			
		}
	}
}