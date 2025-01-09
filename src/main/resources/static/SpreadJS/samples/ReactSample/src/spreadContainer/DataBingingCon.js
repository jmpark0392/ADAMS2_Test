import React from 'react';
import {SpreadSheets, Worksheet, Column} from '@mescius/spread-sheets-react';
import './Style.css'
import dataService from '../dataService'

function DataBingingCon(props) {
    const hostStyle = {
        top: '90px',
        bottom: '0px'
    };
    const autoGenerateColumns = false;
    const data = dataService.getAirpotsData();

    return (
        <div className="componentContainer" style={props.style}>
            <h3>데이터 바인딩</h3>
            <div>
                <p>래 샘플은 데이터 바인딩을 사용하는 방법을 보여줍니다.</p>
            </div>
            <div className="spreadContainer" style={hostStyle}>
                <SpreadSheets>
                    <Worksheet dataSource = {data} name = "All Data"/>
                    <Worksheet dataSource = {data}  name="Part Data" autoGenerateColumns={autoGenerateColumns}>
                        <Column dataField="name" headerText="Name"/>
                        <Column dataField="city" headerText="City"/>
                        <Column dataField="state" headerText="State"/>
                        <Column dataField="lat" headerText="Lat"/>
                        <Column dataField="lon" headerText="Lon"/>
                        <Column dataField="vol2011" headerText="Vol2011"/>
                    </Worksheet>
                </SpreadSheets>
            </div>
        </div>

    );
}

export default DataBingingCon