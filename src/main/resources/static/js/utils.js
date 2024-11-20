// 현재날짜시간 함수
function getCurrentDateTime() {
  // 현재 날짜 시간 구하기
  const now = new Date();

  // 년
  const year = now.getFullYear();
  // 월
  const month = (now.getMonth() + 1).toString().padStart(2, "0");
  // 일
  const day = now.getDate().toString().padStart(2, "0");
  // 시
  const hours = now.getHours().toString().padStart(2, "0");
  // 분
  const minutes = now.getMinutes().toString().padStart(2, "0");
  // 초
  const seconds = now.getSeconds().toString().padStart(2, "0");

  return year + month + day + hours + minutes + seconds;
}

// 띄어쓰기 제거함수
function removeSpaces(str) {
  return str.replace(/\s+/g, "");
}

// 띄어쓰기 Validator 함수 생성
function noWhitespaceValidator(value, args) {
  if (/\s/.test(value)) {
    return { valid: false, msg: `${args.column.name}은 띄어쓰기 금지입니다.` };
  }
  return { valid: true, msg: null };
}

// 필수여부 Validator 함수 생성
function createValidator(value, args) {
  if (value === null || value === "") {
    return { valid: false, msg: `${args.column.name}은 필수입니다.` };
  } else {
    return { valid: true, msg: null };
  }
}

// N자릿수 Validator 함수 생성
function nStrLengthValidator(value, args, len) {
  if (value.length !== len) {
    return {
      valid: false,
      msg: `${args.column.name}은 ${len}자릿수를 유지해야합니다.`,
    };
  }
  return { valid: true, msg: null };
}

// 여러개의 validator를 한번에 정의가능
function complexValidator(value, args, validators) {
  //const validators = [createValidator, noWhitespaceValidator];
  for (const validator of validators) {
    const result = validator(value, args);
    if (!result.valid) {
      return result;
    }
  }
  return { valid: true, msg: null };
}

function validateCompositeEditors(targetElm) {
  var validationResults = { valid: true, msg: "" };
  var currentEditor = grid.getCellEditor();
  if (currentEditor) {
    validationResults = currentEditor.validate(targetElm);
  }
  return validationResults;
}
// Custom ComboBox Editor
function ComboBoxEditor(args, dropdownValues) {
  var $select;
  var defaultValue;

  this.init = function () {
    $select = $("<select class='editor-combobox'>")
      .appendTo(args.container)
      .on("keydown.nav", function (e) {
        if (e.key === "Enter" || e.key === "Tab") {
          validateCompositeEditors(e.target);
        }
      });

    // Add options to the select element from passed dropdownValues
    dropdownValues.forEach(function (item) {
      $select.append(
        $("<option></option>").attr("value", item.key).text(item.value)
      );
    });

    $select.focus();
  };

  this.destroy = function () {
    $select.remove();
  };

  this.focus = function () {
    $select.focus();
  };

  this.loadValue = function (item) {
    defaultValue = item[args.column.field];
    $select.val(defaultValue);
  };

  this.serializeValue = function () {
    return $select.val();
  };

  this.applyValue = function (item, state) {
    item[args.column.field] = state;
  };

  this.isValueChanged = function () {
    return $select.val() !== defaultValue;
  };

  this.validate = function () {
    return {
      valid: true,
      msg: null,
    };
  };
  this.init();
}
// Custom Password Editor
function PasswordEditor(args) {
  var $input;
  var defaultValue;
  this.args = args;

  this.init = function () {
    $input = $("<input type='password' class='editor-text' />")
      .appendTo(args.container)
      .on("keydown.nav", function (e) {
        if (e.key === "Enter" || e.key === "Tab") {
          validateCompositeEditors(e.target);
        }
      });

    $input.focus().select();
  };

  this.destroy = function () {
    $input.remove();
  };

  this.focus = function () {
    $input.focus();
  };

  this.getValue = function () {
    return $input.val();
  };

  this.setValue = function (val) {
    $input.val(val);
  };

  this.loadValue = function (item) {
    defaultValue = item[args.column.field] || "";
    $input.val(defaultValue);
    $input[0].defaultValue = defaultValue;
    $input.select();
  };

  this.serializeValue = function () {
    return $input.val();
  };

  this.applyValue = function (item, state) {
    item[args.column.field] = state;
  };

  this.isValueChanged = function () {
    return $input.val() !== defaultValue;
  };

  this.validate = function () {
    if (args.column.validator) {
      let validationResults = args.column.validator($input.val(), args);
      if (!validationResults.valid) return validationResults;
    }
    return {
      valid: !0,
      msg: null,
    };
  };

  this.init();
}

//Select 조회 : $("#inpSearchTxt").val(), '/WRKFIL001M0SelectList'
function parentGetFile(input, url) {
  console.log("Sending AJAX request...");
  var result_data;
  $.ajax({
    type: "post",
    url: url,
    async: false,
    dataType: "json",
    contentType: "application/json; charset=utf-8",
    data: input,
    success: function (data) {
      console.log("AJAX request successful. Data received:", data);
      result_data = data;
      if (data && data.length > 0) {
        console.log("The number of Data :", data.length);
      } else {
        console.log("No data returned from the server.");
      }
    },
    error: function (xhr, status, error) {
      console.error("!!!! ERROR !!!!!", status, error);
      result_data = "fail";
    },
  });
  return result_data;
}

var ajaxRequest = null;

// Insert 함수 : '/WRKFIL001M0InsertList'
function parentInsertFile(addedRow, url) {
    if(ajaxRequest !== null){
        ajaxRequest.abort();
    }
    
ajaxRequest =  $.ajax({
    type: "post",
    url: url,
    async: true,
    dataType: "text", //서버에서 어떤 타입을 받을 것인가
    contentType: "application/json; charset=utf-8", //서버로 어떤 타입을 보낼 것인가
    data: JSON.stringify(addedRow),
    success: function (response) {
      alert("Successfully Insert");
    },
    error: function (error) {
      console.error("Error inserting data:", error);
    },
  });
}
// Update 함수 : '/WRKFIL001M0UpdateList'
function parentUpdateFile(updatedRow, url) {
    if(ajaxRequest !== null){
        ajaxRequest.abort();
    }
 ajaxRequest = $.ajax({
    type: "post",
    url: url,
    async: true,
    dataType: "text", //서버에서 어떤 타입을 받을 것인가
    contentType: "application/json; charset=utf-8", //서버로 어떤 타입을 보낼 것인가
    data: JSON.stringify(updatedRow),
    success: function (response) {
      alert("Successfully Updated");
      console.log("update 테스트js");
    },
    error: function (error) {
      console.error("Error updating data:", error);
    },
  });
}

// Delete 함수
function parentDeleteFile(todeleteRow, url) {
  $.ajax({
    type: "post",
    url: url,
    async: true,
    dataType: "text", //서버에서 어떤 타입을 받을 것인가
    contentType: "application/json; charset=utf-8", //서버로 어떤 타입을 보낼 것인가
    data: JSON.stringify(todeleteRow),
    success: function (response) {
      alert("Successfully Deleted");
    },
    error: function (error) {
      console.error("Error deleting data:", error);
    },
  });
}

function parentCheckData(data, check_cols) {
  var checkr = [];
  for (key in data) {
    if (check_cols.includes(key) && (data[key] === null || data[key] === "")) {
      checkr.push(false);
    } else {
      checkr.push(true);
    }
  }
  return checkr.every((v) => v === true);
}

function focusOnFirstCellWithEditor(columns, rowIndex, isWithMassUpdate) {
  var columnIndexWithEditor = 0;

  const hasEditor = columns[columnIndexWithEditor].editor;
  if (!hasEditor) {
    if (isWithMassUpdate) {
      columnIndexWithEditor = columns.findIndex(function (col) {
        return col.editor && col.massUpdate;
      });
    } else {
      columnIndexWithEditor = columns.findIndex(function (col) {
        return col.editor;
      });
    }
    if (columnIndexWithEditor === -1) {
      throw new Error("We could not find any Editor in your Column Definition");
    } else {
      grid.setActiveCell(rowIndex, columnIndexWithEditor, false);
      if (isWithMassUpdate) {
        // when it's a mass change, we'll activate the last row without scrolling to it
        // that is possible via the 3rd argument "suppressScrollIntoView" set to "true"
        grid.setActiveRow(data.length, columnIndexWithEditor, true);
      }
    }
  }
}

// grid options 수정 함수
function renewOptions(isEdit = true) {
    grid.setOptions({ editable: isEdit }, true, true, true);
}

function renewOptionsGrids(grid, isEdit = true) {
  if(grid === grid1){
    grid1.setOptions({ editable: isEdit }, true, true, true);
  } else if (grid === gridHist){
    gridHist.setOptions({ editable: isEdit }, true, true, true);
  } else if (grid === grid3){
    grid3.setOptions({ editable: isEdit }, true, true, true);
  } else if (grid === grid4){
    grid4.setOptions({ editable: isEdit }, true, true, true);
  } else {
    console.error("Invalid grid selected.");
  }
}

// exportToExcel 함수 정의
function exportToExcel(fileNm, sheetNm = "Sheet1", columns, data) {
  // 컬럼명과 데이터 처리
  const gridData = data.map((row) => {
    const rowData = {};
    columns.forEach((column) => {
      rowData[column.name] = row[column.field];
    });
    return rowData;
  });

  // 워크시트로 변환
  const worksheet = XLSX.utils.json_to_sheet(gridData);

  // 새 워크북 생성
  const workbook = XLSX.utils.book_new();
  XLSX.utils.book_append_sheet(workbook, worksheet, sheetNm);

  // 워크북을 엑셀 파일로 작성하고 다운로드 트리거
  XLSX.writeFile(workbook, `${fileNm}.xlsx`);
}

/*===========================================
 * 입력값이 NULL이면 "" 리턴
 * @param obj   Object
 * @return true : Null 또는 공백
 ===========================================*/
function isNull(obj) {
    if (obj == null || obj == "") {
        return "";  
    }
    return obj;
}

/*===========================================
 * 이메일 형식을 검증하는 함수
 * @param obj   Object
 * @return true : true
 ===========================================*/
function validateEmail(email) {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}



/*===========================================
 * ajax 호출용 공통 Transaction 함수
 * @param option   Object
 * @param callbackFn   Function
 * @return true : true
 ===========================================*/
function gf_Transaction(option, callbackFn ) {
  console.log("Sending AJAX request...");
  
  var sType        = "post";
  var sUrl         = "t"; 
  var bAsync       = true; 
  var sDataType    = "json"; 
  var sContentType = "application/json; charset=utf-8"; 
  
  if ( option.type != null && option.type != "" && typeof option.type !== 'undefined' ) {
      sType = option.type;
  }
  if ( option.url != null && option.url != "" && typeof option.url !== 'undefined' ) {
      sUrl = option.url;
  }
  if ( option.async != null && option.async != "" && typeof option.async !== 'undefined' ) {
      bAsync = option.async;
  }
  if ( option.dataType != null && option.dataType != "" && typeof option.dataType !== 'undefined' ) {
      sDataType = option.dataType;
  }
  if ( option.contentType != null && option.contentType != "" && typeof option.contentType !== 'undefined' ) {
      sContentType = option.contentType;
  }
  if ( option.data == null || option.data == "" && typeof option.data === 'undefined' ) {
      console.log("Data not Found :", data);
      return;
  }  
  
  var result = {};
  $.ajax({
           type       : sType
         , url        : sUrl
         , async      : bAsync
         , dataType   : sDataType
         , contentType: sContentType
         , data       : option.data
         , success    : function (data) {
                            console.log("AJAX request successful. Data received:", data);
                            if (data && data.length > 0) {
                                console.log("The number of Data :", data.length);
                            } else {
                                console.log("No data returned from the server.");
                            }
                            result.resultCd = "S";
                            result.id       = option.id;
                            result.data     = data;
                            
                            if(typeof  callbackFn === 'function' ){
								callbackFn(result);
							}
                        }
         , error      : function (xhr, status, error) {
		                    console.error("!!!! ERROR !!!!!", status, error);
                            gf_errorHandler(xhr);
                            
                            result.resultCd = "E";
                            result.id       = option.id;
                            result.xhr      = xhr;
                            result.status   = status;
                            result.error    = error;
                            
                            if(typeof  callbackFn === 'function' ){
								callbackFn(result);
							}

		                }
  });
  
}


/*===========================================
 * 그리드 select Editor 목록에 빈값 생성
 * @param obj   Object
 * @return true : true
 ===========================================*/
function gf_PopulateSelect(select, dataSource, addBlank) {
     var index, len, newOption;
     if (addBlank) { select.appendChild(new Option('', '')); }
     $.each(dataSource, function (value, text) {
          newOption = new Option(text, value);
         select.appendChild(newOption);
    });
}
/*===========================================
 * 그리드 select Editor 목록 생성
 * @param obj   Object
 * @return true : true
 ===========================================*/
function gf_Select2Editor(args) {
    var $input;
    var defaultValue;
    var scope = this;
    var calendarOpen = false;
    this.keyCaptureList = [ Slick.keyCode.UP, Slick.keyCode.DOWN, Slick.keyCode.ENTER ];
    this.init = function () {
        $input = $('<select></select>');
        $input.width(args.container.clientWidth + 3);
        gf_PopulateSelect($input[0], args.column.dataSource, true);
        $input.appendTo(args.container);
        $input.focus().select();

        $input.select2({
            placeholder: '-',
            allowClear: true
        });
    };
    this.destroy = function () {
        $input.select2('close');
        $input.select2('destroy');
        $input.remove();
    };
    this.show = function () {
    };
    this.hide = function () {
    };
    this.position = function (position) {
    };
    this.focus = function () {
        $input.select2('input_focus');
    };
    this.loadValue = function (item) {
        defaultValue = item[args.column.field];
        $input.val(defaultValue);
        $input[0].defaultValue = defaultValue;
        $input.trigger("change.select2");
    };
    this.serializeValue = function () {
        return $input.val();
    };
    this.applyValue = function (item, state) {
        item[args.column.field] = state;
    };
    this.isValueChanged = function () {
        return (!($input.val() == "" && defaultValue == null)) && ($input.val() != defaultValue);
    };
    this.validate = function () {
        return {
            valid: true,
            msg: null
        };
    };
    this.init();
}
 
/*===========================================
 * 그리드 select 콤보박스 포멧
 * @param obj   Object
 * @return true : true
 ===========================================*/
function gf_Select2Formatter(row, cell, value, columnDef, dataContext) {
    return columnDef.dataSource[value] || '-';
}

function currencyFormatter(row, cell, value, columnDef, dataContext) {
    if (value == null || value === "") {
        return "";
    }
    // 숫자 형식으로 변환
    var number = parseFloat(value);

    // 금액 형식으로 변환 (예: 1,234,567)
    var formattedNumber = number.toLocaleString('ko-KR', {
        style: 'decimal',
		minimumFractionDigits: 2,
		maximumFractionDigits: 2
    });

    return formattedNumber;
}

function getCycleFromYymm(stdYymm){
	const Month = stdYymm.slice(-2);
	const quartlyList = ['03', '06', '09', '12'];
	const yearMonth = '12';
	const halfMonth = '06';
	var cycleList = ['Monthly',];
	
	if(Month.length == 2){
		if(quartlyList.includes(Month)){
		  cycleList.push("Quarterly");
		}
		
		if(yearMonth == Month){
		  cycleList.push("Yearly");
		}
		
		if(halfMonth == Month){
		  cycleList.push("Halfly");
		}
		return cycleList;
	}
	else {
		console.log("Month is invalid.");
	}
}

function getUnqIds(data, unqId){
	var idList = [];
	for	(var i = 0; i < data.length; i++){
		idList.push(data[i][unqId])
	}
	return idList
}

function fn_getDataForTree(data, unqId, upprId, indentCol, indentFactor = 1){
	var allIds = getUnqIds(data, unqId);
	for	(var i = 0; i < data.length; i++)
	{
		var d = data[i];
		d["indent"] = parseInt(d[indentCol]) * indentFactor;
		
		if(d[indentCol] == 0){
			d["parent"] = null;
		}
		else {
			d["parent"] = allIds.indexOf(d[upprId]);
		}
		d["_collapsed"] = false;
	} 
	return data
}

var TreeArchFormatter = function (row, cell, value, columnDef, dataContext, grid) {
	if (value == null || value == undefined || dataContext === undefined) { return ""; }
	value = value.replace(/&/g,"&amp;").replace(/</g,"&lt;").replace(/>/g,"&gt;");
	var spacer = "<span style='display:inline-block;height:1px;width:" + (15 * dataContext["indent"]) + "px'></span>";
	var idx = dataView.getIdxById(dataContext[unqId]);
	var data = grid.getData().items;
	var hasChildren = (data[idx + 1] && data[idx + 1].indent > data[idx].indent);
	
	if (hasChildren) {
		if (dataContext._collapsed) {
			return spacer + " <span class='toggle sgi sgi-plus-box-outline'></span>&nbsp;" + value;
		} 
		else {
			return spacer + " <span class='toggle sgi sgi-minus-box-outline'></span>&nbsp;" + value;
		}
	}
	else {
		return spacer + " <span class='toggle'></span>&nbsp;" + value;
	}
};

// 필터링 함수
function treeFilter(item) {
	//console.log("treeFilter's item: ", item);
	var data = grid.getData().items;
	if (item.parent != null) {
		var parent = data[item.parent];
		
		while (parent) {
			if (parent._collapsed) {
				return false;
			}
			parent = data[parent.parent];
		}
		
	}
	return true;
}

/*===========================================
 * service callback error Handler function
 * @param obj   xhr
 ===========================================*/
function gf_errorHandler(xhr) {
	if (xhr.status == "403" || xhr.status == "404") {
		$("#pageName").val("error/error_400");
	} else if (xhr.status == "500" || xhr.status == "503") {
		$("#pageName").val("error/error_500");
	} else if (xhr.responseText == "FAIL_AUTHENTIC") {
		$("#form").attr("action","/FailAuthentic");	
	} else if (xhr.responseText == "FAIL_CSRFCERT") {
		$("#form").attr("action","/FailCsrfCertificattion");
	} else {
		$("#pageName").val("error/error");
	}
	
	$("#form").submit();
}

function createPagination(objId, vPageCnt, vPageNum) {
			
	var pageObj = $("#"+objId);
	var prevDisabled = false;
	var nextDisabled = false;
	
	var prevObj = "<li class='page-item'>";
	prevObj += "<a class='page-link'>";
	prevObj += "<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='icon'>";
	prevObj += "<path stroke='none' d='M0 0h24v24H0z' fill='none'></path><path d='M15 6l-6 6l6 6'></path>";
	prevObj += "</svg>";
	prevObj += "prev";

	var nextObj = "<li class='page-item'>";
	nextObj += "<a class='page-link'>";
	nextObj += "next";
	nextObj += "<svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' fill='none' stroke='currentColor' stroke-width='2' stroke-linecap='round' stroke-linejoin='round' class='icon'>";
	nextObj += "<path stroke='none' d='M0 0h24v24H0z' fill='none'></path><path d='M9 6l6 6l-6 6'></path>";
	nextObj += "</svg>";
	
	pageObj.children().remove();
	
	pageObj.append(prevObj);
	for (var i=1; i<=vPageCnt; i++) {
		var tmpObj = "";
		if (i == vPageNum) {
			tmpObj = "<li class='page-item active'><a class='page-link'>"+i+"</a></li>";
			if (i == 1) {
				prevDisabled = true;
			} 
			if (vPageCnt == vPageNum) {
				nextDisabled = true;
			}
		} else {
			tmpObj = "<li class='page-item'><a class='page-link'>"+i+"</a></li>";
		}
		pageObj.append(tmpObj);
	}
	pageObj.append(nextObj);
	
	if (vPageCnt == 0) {
		prevDisabled = true;
		nextDisabled = true;
	}
	
	if (prevDisabled) {
		pageObj.children().first().attr("class", "page-item disabled");
	}
	
	if (nextDisabled) {
		pageObj.children().last().attr("class", "page-item disabled");
	}

}