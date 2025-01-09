import React, { useState } from 'react';
import './Sample.css'
import NavBar from './NavBar'
import QuickStart from './spreadContainer/QuickStart'
import SpreadSheetsCon from './spreadContainer/SpreadSheetsCon'
import WorkSheetCon from './spreadContainer/WorkSheetCon'
import ColumnCon from './spreadContainer/ColumnCon'
import DataBingingCon from './spreadContainer/DataBingingCon'
import StyleCon from './spreadContainer/StyleCon'
import OutlineCon from './spreadContainer/OutlineCon'

function Sample() {
    const [activeIndex, setActiveIndex] = useState(0);
    const spreadComponents = [
        <QuickStart />,
        <SpreadSheetsCon />,
        <WorkSheetCon />,
        <ColumnCon />,
        <DataBingingCon />,
        <StyleCon />,
        <OutlineCon />,
    ];

    const changeActiveIndex = (index) => {
        setActiveIndex(index);
    }

    return (
        <div className='app-container'>
            <Header />
            <div className="body-container">
                <NavBar
                    activeIndex={activeIndex}
                    changeActiveIndex={changeActiveIndex}
                />
                {spreadComponents[activeIndex]}
            </div>
            <Footer />
        </div>
    );
}

function Header() {
    return (
        <div className="app-header">
            <img className="logo" src="./img/logo.png" alt="logo" />
            <span className="header-text">SpreadJS React Sample</span>
        </div>
    );
}

function Footer() {
    return (
        <div className="app-footer">
            <span className="footer-text">© 2024 MESCIUS inc. All Rights Reserved. All product and company names herein may be trademarks of their respective owners.</span>
        </div>
    );
}

export default Sample