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
		var tbWidth = 0;
		for (idx in this.ds_header) {
			if (this.ds_header[idx].visible == true) {
				tmp_thead += "<th field='"+this.ds_header[idx].field+"' style='width:"+this.ds_header[idx].width+"px; text-align:center;'>"+this.ds_header[idx].name+"</th>";
				tbWidth += this.ds_header[idx].width
			}
		}
		tmp_thead += "</tr></thead>";
		
		$("#"+this.gridId).append(tmp_thead);
		$("#"+this.gridId).css("width", tbWidth+"px");

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
					var field = this.ds_header[j].field;
					var type = this.ds_header[j].type;
					var align = this.ds_header[j].align;

					tmp_tr += "<td align='"+align+"'>";
					if ( type == "text") {
						if (this.ds_header[j].editable == false) {
							editable = "readonly";
						} else {
							editable = "";
						}
						tmp_tr += "<input type='text' name='"+this.ds_header[j].field+"' value='"+cellValue+"' "+editable+" class='form-control p-1 px-2 w-100 text-wrap' />";
					} else if (type == "check") {
						var checked="";
						if (cellValue == "Y") {
							checked = "checked='checked'";
						}
						if (this.ds_header[j].editable == false) {
							editable = "disabled";
						}
						tmp_tr += "<input type='checkbox' name='"+this.ds_header[j].field+"' "+checked+" "+editable+" value='Y' class='form-check-input' />";
						tmp_tr += "<input type='hidden' name='_"+this.ds_header[j].field+"' value='N'/>";
					} else if (type == "none") {
						if (field == statusName) {
							tmp_tr += "";
						} else {
							tmp_tr += cellValue;
							tmp_tr += "<input type='hidden' name='"+this.ds_header[j].field+"' value='"+cellValue+"'/>";
						}
					} else if (type == "calendar") {
						tmp_tr += "<div class='input-icon mb-2'>";
						tmp_tr += "<input class='form-control' placeholder='Select a date' id='datepicker-icon' value='"+cellValue+"'>";
						tmp_tr += "<span class='input-icon-addon'>";
						tmp_tr += "<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='icon icon-1'>";
						tmp_tr += "<path d='M4 7a2 2 0 0 1 2 -2h12a2 2 0 0 1 2 2v12a2 2 0 0 1 -2 2h-12a2 2 0 0 1 -2 -2v-12z'></path>";
						tmp_tr += "<path d='M16 3v4'></path>";
						tmp_tr += "<path d='M8 3v4'></path>";
						tmp_tr += "<path d='M4 11h16'></path>";
						tmp_tr += "<path d='M11 15h1'></path>";
						tmp_tr += "<path d='M12 15v3'></path>";
						tmp_tr += "</svg>";
						tmp_tr += "</span>";
						tmp_tr += "</div>";
					}
					tmp_tr += "</td>";
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
		
		if (afterFn != "") {
			$(document).on("change", "input[type=text],input[type=checkbox]", function () {
				eval(afterFn+"()");
			});
		}
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
					} else if (type == "none") {
						body[field] = tmpObj.val();
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