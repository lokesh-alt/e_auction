import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'auction-frontend';
  
    constructor() {
    this.title = 'Auction Frontend - Angular Application';
  }
  
}
