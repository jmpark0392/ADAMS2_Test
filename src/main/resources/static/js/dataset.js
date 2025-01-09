var statusName = "rowStatus";
function DataSet() {
	
	var ds_header;
	var ds_body = [];
	var org_ds_body = [];
	
	this.setHeader = function (header) {
		this.ds_header = header;
	}
	
	this.drawGrid = function (sheet, data, headerHeight, rowHeight) {
		if (this.ds_header == undefined || this.ds_header.length <= 0) {
			return false;
		}
		
		var colSize = this.ds_header.length;
		this.ds_body = [];
		this.org_ds_body = [];

		sheet.setColumnCount(colSize);
		sheet.setRowCount(data.length);
		
		for (var idx=0; idx<colSize; idx++) {
			sheet.setValue(0, idx, this.ds_header[idx].name, GC.Spread.Sheets.SheetArea.colHeader);
			sheet.setColumnWidth(idx, this.ds_header[idx].width);
			if (!this.ds_header[idx].visible) {
				sheet.setColumnVisible(idx, false);
			} else {
				sheet.setColumnVisible(idx, true);
			}
		}
		
		sheet.getRange(0, 0, 1, colSize, GC.Spread.Sheets.SheetArea.colHeader).font = "11pt 맑은 고딕";
		sheet.getRange(0, 0, 1, colSize).hAlign(GC.Spread.Sheets.HorizontalAlign.center);
		sheet.getRange(0, 0, 1, colSize).vAlign(GC.Spread.Sheets.VerticalAlign.center);
		sheet.setRowHeight(0, headerHeight, GC.Spread.Sheets.SheetArea.colHeader);
		
		for (var i=0; i<data.length; i++) {
			var rowData = data[i];
			var currBody = {};
			var orgBody = {};
			for (var j = 0; j<this.ds_header.length; j++) {
				var cellValue = eval("rowData."+this.ds_header[j].field);
				sheet.setValue(i, j, cellValue);
				currBody[this.ds_header[j].field] = cellValue;
				orgBody[this.ds_header[j].field] = cellValue;
			}
			sheet.setRowHeight(i, rowHeight);
			this.ds_body.push(currBody);
			this.org_ds_body.push(orgBody);
		}
		
		sheet.getRange(0, 0, data.length, colSize).hAlign(GC.Spread.Sheets.HorizontalAlign.center);
		sheet.getRange(0, 0, data.length, colSize).vAlign(GC.Spread.Sheets.VerticalAlign.center);
		spread.options.allowDragHeaderToMove = GC.Spread.Sheets.AllowDragHeaderToMove.both;
		
		spread.resumePaint();
	}
	
	this.gridSerialize = function (sheet, key) {
		var rowCount = sheet.getRowCount();
		var columnCount = this.ds_header.length;
		this.ds_body = [];
		
		for (var row=0; row<rowCount; row++) {
		    var body = {};
		    for (var col=0; col<columnCount; col++) {
				var name = this.ds_header[col].name;
				var field = this.ds_header[col].field;
		        body[field] = sheet.getValue(row, this.getCIndex(sheet,name));
		    }
			this.ds_body.push(body);
		}

		this.setStatus(key);
		
		if (this.ds_body.length>0) {
			return JSON.stringify(this.ds_body);	
		} else {
			return "";
		}
	}
	
	this.getCIndex = function(sheet,name) {
		var columnCount = sheet.getColumnCount();
		for (var col=0; col<columnCount; col++) {
			if (sheet.getValue(0,col,GC.Spread.Sheets.SheetArea.colHeader)==name) {
				return col;
			}
		}
	}
	
	this.setStatus = function(key) {
		var orgRowCnt = this.org_ds_body.length;
		var currRowCnt = this.ds_body.length;
		
		for (var rowIdx=0; rowIdx<currRowCnt; rowIdx++) {
			var currBody = this.ds_body[rowIdx];
			if (currBody[key] == null || currBody[key] === "") {
				currBody[statusName] = "A";
			}
		}

		for (var rowIdx=0; rowIdx<orgRowCnt; rowIdx++) {
			var orgBody = this.org_ds_body[rowIdx];
			var currBody = this.getBodyMatchKey(key, orgBody[key]);
			
			if (currBody === "") {
				currBody = orgBody;
				currBody[statusName] = "D";
				console.log(currBody);
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
	
	this.getBodyMatchKey = function(key, value) {
		for (var i=0; i<this.ds_body.length; i++) {
			var tempBody = this.ds_body[i];
			if (tempBody[key] == value) {
				return tempBody;
			} else {
				continue;
			}
		}
		return "";
	}
	
	this.isModified = function(orgBody, currBody) {
		for (var colIdx=0; colIdx<this.ds_header.length; colIdx++) {
			if (this.ds_header[colIdx] == statusName) {
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
}