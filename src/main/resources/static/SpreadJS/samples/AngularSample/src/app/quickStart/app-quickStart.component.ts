import {Component} from "@angular/core";
import {DataService} from "../app-data.service";
import { SpreadSheetsModule } from "@mescius/spread-sheets-angular";
@Component({
    standalone: true,
    imports: [SpreadSheetsModule],
    providers: [DataService],
    templateUrl: './app-quickStart.component.html'
})
export class QuickStartComponent {
    spreadBackColor = 'aliceblue';
    sheetName = 'Person Address';
    hostStyle = {
        top: '200px',
        bottom: '10px'
    };
    data: any;
    autoGenerateColumns = false;

    constructor(private dataservice: DataService) {
        this.data = dataservice.getPersonAddressData();
    }
}
