import React, { useState } from 'react';
import {SpreadSheets, Worksheet, Column} from '@mescius/spread-sheets-react';
import './Style.css'
import dataService from '../dataService'

function WorkSheetCon(props) {
    const hostStyle = {
        top: '90px',
        bottom: '172px'
    };
    const autoGenerateColumns = false;
    const data = dataService.getPersonAddressData();

    const [workSheetSetting, setWorkSheetSetting] = useState({
        rowHeaderVisible: true,
        columnHeaderVisible: true,
        frozenRowCount: 3,
        frozenColumnCount: 2,
        frozenTrailingRowCount: 0,
        frozenTrailingColumnCount: 0,
        sheetTabColor: '#61E6E6',
        frozenlineColor: '#000000',
        selectionBackColor: '#D0D0D0',
        selectionBorderColor: '#217346'
    });

    const propChangeHandler = (prop, value) => {
        setWorkSheetSetting({
            ...workSheetSetting,
            [prop]: value,
        });
    }

    return (
        <div className="componentContainer" style={props.style}>
            <h3>GC-Worksheet</h3>
            <div>
                <p>아래 샘플은 시트의 일부 속성을 바인딩하는 방법을 보여줍니다.</p>
            </div>
            <div className="spreadContainer" style={hostStyle}>
                <SpreadSheets>
                    <Worksheet rowCount={workSheetSetting.rowCount} colCount={workSheetSetting.colCount}
                                frozenRowCount={workSheetSetting.frozenRowCount}
                                frozenColumnCount={workSheetSetting.frozenColumnCount}
                                rowHeaderVisible={workSheetSetting.rowHeaderVisible}
                                columnHeaderVisible={workSheetSetting.columnHeaderVisible}
                                frozenTrailingRowCount={workSheetSetting.frozenTrailingRowCount}
                                frozenTrailingColumnCount={workSheetSetting.frozenTrailingColumnCount}
                                sheetTabColor={workSheetSetting.sheetTabColor} frozenlineColor={workSheetSetting.frozenlineColor}
                                selectionBackColor={workSheetSetting.selectionBackColor}
                                selectionBorderColor={workSheetSetting.selectionBorderColor}
                                dataSource={data} autoGenerateColumns={autoGenerateColumns}>
                        <Column width={150} dataField="Name"/>
                        <Column width={150} dataField="CountryRegionCode"/>
                        <Column width={100} dataField="City"/>
                        <Column width={200} dataField="AddressLine"/>
                        <Column width={100} dataField="PostalCode"/>
                    </Worksheet>
                </SpreadSheets>
            </div>
            <div className="settingContainer">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <label><input type="checkbox" checked={workSheetSetting.rowHeaderVisible} onChange={(e) => {propChangeHandler('rowHeaderVisible', e.target.checked)}}/>행 머리글 표시</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={workSheetSetting.columnHeaderVisible} onChange={(e) => {propChangeHandler('columnHeaderVisible', e.target.checked)}}/>열 머리글 표시</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="text" value={workSheetSetting.frozenRowCount} onChange={(e) => {propChangeHandler('frozenRowCount', e.target.value)}}/>고정된 행 수</label>
                        </td>
                        <td>
                            <label><input type="text" value={workSheetSetting.frozenColumnCount} onChange={(e) => {propChangeHandler('frozenColumnCount', e.target.value)}}/>고정된 열 수</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="text" value={workSheetSetting.frozenTrailingRowCount} onChange={(e) => {propChangeHandler('frozenTrailingRowCount', e.target.value)}}/>고정된 후행 행 수</label>
                        </td>
                        <td>
                            <label><input type="text" value={workSheetSetting.frozenTrailingColumnCount} onChange={(e) => {propChangeHandler('frozenTrailingColumnCount', e.target.value)}}/>고정된 후행 열 수</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input value={workSheetSetting.sheetTabColor} type="color" onChange={(e) => {propChangeHandler('sheetTabColor', e.target.value)}}/> 시트 탭 색
                        </td>
                        <td>
                            <input value={workSheetSetting.frozenlineColor} type="color" onChange={(e) => {propChangeHandler('frozenlineColor', e.target.value)}}/> 고정된 선 색
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input value={workSheetSetting.selectionBackColor} type="color" onChange={(e) => {propChangeHandler('selectionBackColor', e.target.value)}}/> 선택 영역 배경색
                        </td>
                        <td>
                            <input value={workSheetSetting.selectionBorderColor} type="color" onChange={(e) => {propChangeHandler('selectionBorderColor', e.target.value)}}/> 선택 영역 테두리 색
                        </td>
                    </tr>
                    </tbody>
                </table>

            </div>
        </div>
    );
}

export default WorkSheetCon