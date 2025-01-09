import React, {useState} from 'react';
import {SpreadSheets, Worksheet, Column} from '@mescius/spread-sheets-react';
import './Style.css'
import dataService from '../dataService'

function SpreadSheetsCon(props) {
    const [spreadSheetSetting, setSpreadSheetSetting] = useState({
        newTabVisible: true,
        tabStripVisible: true,
        tabStripRatio: true,
        tabNavigationVisible: true,
        scrollbarShowMax: true,
        scrollbarMaxAlign: true,
        showHorizontalScrollbar: true,
        showVerticalScrollbar:true,
        allowUserZoom : true,
        allowUserResize : true,
        spreadBackColor: '#FFFFFF',
        grayAreaBackColor: '#E4E4E4',
    });
    const hostStyle = {
        top: '90px',
        bottom: '180px'
    };
    const autoGenerateColumns = false;
    const data = dataService.getPersonAddressData();

    const propChangeHandler = (prop, value) => {
        setSpreadSheetSetting({
            ...spreadSheetSetting,
            [prop]: value,
        });
    };

    return (
        <div className="componentContainer" style={props.style}>
            <h3>GC-Spread-Sheets</h3>
            <div>
                <p>아래 샘플에서는 스프레드의 일부 속성을 바인딩하는 방법을 보여 줍니다.</p>
            </div>
            <div className="spreadContainer" style={hostStyle}>
                <SpreadSheets newTabVisible={spreadSheetSetting.newTabVisible}
                                tabStripVisible={spreadSheetSetting.tabStripVisible}
                                tabStripRatio={spreadSheetSetting.tabStripRatio}
                                tabNavigationVisible={spreadSheetSetting.tabNavigationVisible}
                                scrollbarMaxAlign={spreadSheetSetting.scrollbarMaxAlign}
                                scrollbarShowMax={spreadSheetSetting.scrollbarShowMax}
                                showHorizontalScrollbar={spreadSheetSetting.showHorizontalScrollbar}
                                showVerticalScrollbar={spreadSheetSetting.showVerticalScrollbar}
                                backColor={spreadSheetSetting.spreadBackColor} grayAreaBackColor={spreadSheetSetting.grayAreaBackColor}
                                allowUserZoom={spreadSheetSetting.allowUserZoom} allowUserResize={spreadSheetSetting.allowUserResize}>
                    <Worksheet dataSource={data}
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
                            <label><input type="checkbox" checked={spreadSheetSetting.newTabVisible} onChange={(e)=>{propChangeHandler('newTabVisible',e.target.checked)}}/>새 탭 표시</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.tabStripVisible} onChange={(e)=>{propChangeHandler('tabStripVisible',e.target.checked)}}/>탭 스트립 표시</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.tabStripRatio} onChange={e => {propChangeHandler('tabStripRatio', e.target.checked)}}/>tabStripRatio</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.tabNavigationVisible} onChange={e => {propChangeHandler('tabNavigationVisible', e.target.checked)}}/>tabNavigationVisible</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.scrollbarShowMax} onChange={e => {propChangeHandler('scrollbarShowMax', e.target.checked)}}/>scrollbarShowMax</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.scrollbarMaxAlign} onChange={e => {propChangeHandler('scrollbarMaxAlign', e.target.checked)}}/>scrollbarMaxAlign</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.showHorizontalScrollbar} onChange={(e)=>{propChangeHandler('showHorizontalScrollbar',e.target.checked)}}/>가로 스크롤 막대 표시</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.showVerticalScrollbar} onChange={(e)=>{propChangeHandler('showVerticalScrollbar',e.target.checked)}}/>세로 스크롤 막대 표시</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.allowUserZoom} onChange={(e)=>{propChangeHandler('allowUserZoom',e.target.checked)}}/>사용자 확대/측소 허용</label>
                        </td>
                        <td>
                            <label><input type="checkbox" checked={spreadSheetSetting.allowUserResize} onChange={(e)=>{propChangeHandler('allowUserResize',e.target.checked)}}/>사용자 크기 조정 허용</label>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input value={spreadSheetSetting.grayAreaBackColor} type="color" onChange={(e)=>{propChangeHandler('grayAreaBackColor',e.target.value)}}/> 회색 영역 배경색
                        </td>
                        <td>
                            <input value={spreadSheetSetting.spreadBackColor} type="color" onChange={(e)=>{propChangeHandler('spreadBackColor',e.target.value)}}/> 배경색
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    );
}

export default SpreadSheetsCon