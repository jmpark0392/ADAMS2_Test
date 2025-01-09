import React from 'react';
import { SpreadSheets, Worksheet, Column } from '@mescius/spread-sheets-react';
import './Style.css'
import dataService from '../dataService'

function QuickStart(props) {
    const spreadBackColor = 'aliceblue';
    const sheetName = 'Person Address';
    const hostStyle = {
        top: '240px',
        bottom: '10px'
    };
    const autoGenerateColumns = false;
    const data = dataService.getPersonAddressData();

    return (
        <div className="componentContainer" style={props.style}>
            <h3>빠른 시작</h3>
            <div>
                React 응용 프로그램에서 스프레드시트를 시작하는 방법은 다음과 같습니다.
                <div>
                    <p>1. React 응용 프로그램에 참조 파일을 추가합니다.</p>
                    <p>2. 데이터와 논리를 제공할 컴포넌트를 추가합니다.</p>
                    <p>3. 데이터를 스프레드의 몇 가지 기타 옵션과 바인딩합니다.</p>
                    <p>4. 몇 가지 CSS를 추가하여 모양을 사용자 정의합니다.</p>
                </div>
            </div>
            <div className="spreadContainer" style={hostStyle}>
                <SpreadSheets backColor={spreadBackColor}>
                    <Worksheet name={sheetName} dataSource={data}
                        autoGenerateColumns={autoGenerateColumns}>
                        <Column width={150} dataField="Name" />
                        <Column width={150} dataField="CountryRegionCode" />
                        <Column width={100} dataField="City" />
                        <Column width={200} dataField="AddressLine" />
                        <Column width={100} dataField="PostalCode" />
                    </Worksheet>
                </SpreadSheets>
            </div>
        </div>

    );
}

export default QuickStart