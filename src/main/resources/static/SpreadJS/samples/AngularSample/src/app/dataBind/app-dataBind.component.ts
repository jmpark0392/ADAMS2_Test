import {Component} from "@angular/core";
import {DataService} from "../app-data.service";
import {SpreadSheetsModule} from "@mescius/spread-sheets-angular";
import {FormsModule} from "@angular/forms";
@Component({
    standalone: true,
    imports: [FormsModule, SpreadSheetsModule],
    providers: [DataService],
    templateUrl: './app-dataBind.component.html'
})
export class DataBindComponent {
    hostStyle = {
        top: '90px',
        bottom: '0px'
    };
    data: any;
    autoGenerateColumns = false;

    constructor(private dataService: DataService) {
        this.data = dataService.getAirpotsData();
    }
}
