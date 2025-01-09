import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './Sample';
import '@mescius/spread-sheets/styles/gc.spread.sheets.excel2013white.css';
import GC from '@mescius/spread-sheets';
import '@mescius/spread-sheets-resources-ko';

GC.Spread.Common.CultureManager.culture("ko-kr");

const container = document.getElementById('root');

const root = ReactDOM.createRoot(container);
root.render(<App />);
