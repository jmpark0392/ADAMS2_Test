import React, {useState} from 'react';
import {SpreadSheets, Worksheet, Column} from '@mescius/spread-sheets-react';
import './Style.css'
import dataService from '../dataService'

function OutlineCon(props) {
    const [outlineOption, setOutlineOption] = useState({
        showRowOutline: true,
        showColumnOutline: true
    });
    const hostStyle = {
        top: '90px',
        bottom: '35px'
    };
    const rowOutlineInfo = [{index: 1, count: 4}, {index: 6, count: 4}];
    const columnOutlineInfo = [{index: 0, count: 4}];
    const autoGenerateColumns = false;
    const data = dataService.getPersonAddressData();

    const propChangeHandler = (prop, value) => {
        setOutlineOption({
            ...outlineOption,
            [prop]: value,
        });
    };

    return (
        <div className="componentContainer" style={props.style}>
            <h3>윤곽선</h3>
            <div>
                <p>아래 샘플은 윤곽선을 사용하는 방법을 보여줍니다.</p>
            </div>
            <div className="spreadContainer" style={hostStyle}>
                <SpreadSheets>
                    <Worksheet
                        showRowOutline = {outlineOption.showRowOutline}
                        showColumnOutline = {outlineOption.showColumnOutline}
                        rowOutlineInfo = {rowOutlineInfo}
                        columnOutlineInfo = {columnOutlineInfo}
                        dataSource={data}
                        autoGenerateColumns={autoGenerateColumns}>
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
                            <label><input type="checkbox" checked={outlineOption.showRowOutline} onChange={(e) => {propChangeHandler('showRowOutline', e.target.checked)}}/>행 개요 표시</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={outlineOption.showColumnOutline} onChange={(e) => {propChangeHandler('showColumnOutline', e.target.checked)}}/>열 개요 표시</label>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default OutlineCon