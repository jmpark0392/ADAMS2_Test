import {Component} from "@angular/core";
import {SpreadSheetsModule} from "@mescius/spread-sheets-angular";
import {FormsModule} from "@angular/forms";
@Component({
    standalone: true,
    imports: [FormsModule, SpreadSheetsModule],
    templateUrl: './app-column.component.html'
})
export class ColumnComponent {
    hostStyle = {
        top: '90px',
        bottom: '74px'
    };
    visible = true;
    resizable = true;
    width = 300;
    formatter = '$ #.00';
    data: any;
    autoGenerateColumns = false;

    constructor() {
        let dataTable = [];
        for (let i = 0; i < 42; i++) {
            dataTable.push({price: i + 0.56})
        }
        this.data = dataTable;
    }
}
