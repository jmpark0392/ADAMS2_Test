import React, {useState} from 'react';
import {SpreadSheets, Worksheet, Column} from '@mescius/spread-sheets-react';
import './Style.css'

function ColumnCon(props) {
    const [columnOption, setColumnOption] = useState({
        visible: true,
        resizable: true,
        width: 300,
        formatter: '$ #.00'
    });
    const hostStyle = {
        top: '90px',
        bottom: '74px'
    };
    const autoGenerateColumns = false;
    let dataTable = [];
    for (let i = 0; i < 42; i++) {
        dataTable.push({price: i + 0.56})
    }
    const data = dataTable;

    const propChangeHandler = (prop, value) => {
        setColumnOption({
            ...columnOption,
            [prop]: value,
        });
    };

    return (
        <div className="componentContainer" style={props.style}>
            <h3>GC-Column</h3>
            <div>
                <p>아래 샘플은 열의 일부 속성을 바인딩하는 방법을 보여줍니다.</p>
            </div>
            <div className="spreadContainer" style={hostStyle}>
                <SpreadSheets>
                    <Worksheet dataSource={data} autoGenerateColumns={autoGenerateColumns}>
                        <Column dataField="price" width={columnOption.width} formatter = {columnOption.formatter} visible = {columnOption.visible} resizable={columnOption.resizable}/>
                    </Worksheet>
                </SpreadSheets>
            </div>
            <div className="settingContainer">
                <table>
                    <tbody>
                    <tr>
                        <td>
                            <label><input type="checkbox" checked={columnOption.visible} onChange={(e) => {propChangeHandler('visible', e.target.checked)}}/>표시</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={columnOption.resizable} onChange={(e) => {propChangeHandler('resizable', e.target.checked)}}/>크기 조정 가능</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="text" value={columnOption.width} onChange={(e) => {propChangeHandler('width', e.target.value)}}/>너비</label>
                        </td>
                        <td>
                            <label><input type="text" value={columnOption.formatter} onChange={(e) => {propChangeHandler('formatter', e.target.value)}}/>포맷터</label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    )
}

export default ColumnCon