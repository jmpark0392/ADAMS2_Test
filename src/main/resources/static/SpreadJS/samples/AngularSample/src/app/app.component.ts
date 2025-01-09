import {Component} from '@angular/core';
import * as GC from '@mescius/spread-sheets';
import '@mescius/spread-sheets-resources-ko';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
    selector: 'my-app',
    standalone: true,
    imports: [RouterLink, RouterOutlet],
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    appHeader = 'SpreadJS Angular Sample';
    appFooter = '© 2024 MESCIUS inc. All Rights Reserved. All product and company names herein may be trademarks of their respective owners.';

    constructor() {
        GC.Spread.Common.CultureManager.culture('ko-kr');
    }
}
