import { Component, OnInit } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
@Component({
  selector: 'app-btncellrender',
  templateUrl: './btncellrender.component.html',
  styleUrls: ['./btncellrender.component.scss']
})
export class BtncellrenderComponent implements OnInit {
  private params: any;

  agInit(params: any): void {
    this.params = params;
  }

  btnClickedHandler() {
    this.params.clicked(this.params.value);
  }

  ngOnDestroy() {
    // no need to remove the button click handler 
  }
  constructor() { }

  ngOnInit(): void {
  }

}
