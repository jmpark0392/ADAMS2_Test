import {Routes} from "@angular/router";
import {QuickStartComponent} from "../quickStart/app-quickStart.component";
import {SpreadSheetsComponent} from "../spreadSheets/app-spreadSheets.component";
import {WorksheetComponent} from "../worksheet/app-worksheet.component";
import {ColumnComponent} from "../column/app-column.component";
import {DataBindComponent} from "../dataBind/app-dataBind.component";
import {StyleComponent} from "../style/app-style.component";
import {OutlineComponent} from "../outline/app-outline.component";

export const routes: Routes = [
    {path: '', redirectTo: '/quick-start', pathMatch: 'full'},
    {path: 'quick-start', component: QuickStartComponent},
    {path: 'gc-spread-sheets', component: SpreadSheetsComponent},
    {path: 'gc-worksheet', component: WorksheetComponent},
    {path: 'gc-column', component: ColumnComponent},
    {path: 'data-binding', component: DataBindComponent},
    {path: 'style', component: StyleComponent},
    {path: 'outline', component: OutlineComponent}
];
