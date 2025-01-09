import {Component} from "@angular/core";
import {DataService} from "../app-data.service";
import {SpreadSheetsModule} from "@mescius/spread-sheets-angular";
import {FormsModule} from "@angular/forms";
@Component({
    standalone: true,
    imports: [FormsModule, SpreadSheetsModule],
    providers: [DataService],
    templateUrl: './app-worksheet.component.html'
})
export class WorksheetComponent {
    hostStyle = {
        top: '90px',
        bottom: '215px'
    };
    rowHeaderVisible = true;
    columnHeaderVisible = true;
    frozenRowCount = 3;
    frozenColumnCount = 2;
    frozenTrailingRowCount = 0;
    frozenTrailingColumnCount = 0;
    rowCount = 200;
    columnCount = 20;
    sheetTabColor = '#61E6E6';
    frozenlineColor = '#000000';
    selectionBackColor = '#D0D0D0';
    selectionBorderColor = '#217346';
    data: any;
    autoGenerateColumns = false;

    constructor(private dataservice: DataService) {
        this.data = dataservice.getPersonAddressData();
    }
}
