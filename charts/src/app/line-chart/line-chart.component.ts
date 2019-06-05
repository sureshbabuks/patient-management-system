import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, BaseChartDirective, Label } from 'ng2-charts';
import * as pluginAnnotations from 'chartjs-plugin-annotation';
import { ActivatedRoute } from '@angular/router';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})
export class LineChartComponent implements OnInit {
  bp;
  heartRate;

  public lineChartData: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 4], label: 'Blood Pressure' },
  ];
  public lineChartData2: ChartDataSets[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Heart Beat' },
  ];
   public lineChartLabels: Label[] = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];

  public lineChartColors: Color[] = [
    {
      borderColor: 'black',
      backgroundColor: 'rgb(244, 66, 194,0.3)',
    },
  ];

  public lineChartColors2: Color[] = [
    {
      borderColor: 'blue',
      backgroundColor: 'rgb(66, 69, 244,0.3)',
    },
  ];

  public lineChartLegend = true;
  public lineChartType = 'line';
  public lineChartPlugins = [pluginAnnotations];

  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  constructor(private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.connect();

    setInterval(() => {
      this.pushOne();
  }, 1000);

  setInterval(() => {
    this.pushTwo();
}, 1000);
  }

  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);

  }

  public pushOne() {
    this.lineChartData.forEach((x, i) => {
      x.data = x.data.slice(1)
      const data: number[] = x.data as number[];
      data.push(this.bp);
      console.log("BP data: " + data)
      console.log("BP Value: " + this.bp)

    });

     this.lineChartLabels = this.lineChartLabels.slice(1);

     this.lineChartLabels.push(`Label ${this.lineChartLabels.length}`);
    ;
  }

  public pushTwo() {
    this.lineChartData2.forEach((x, i) => {
      x.data = x.data.slice(1)
      const data: number[] = x.data as number[];
      data.push(this.heartRate);
      console.log("Heart Beat Data: " + data)
      console.log("Heart Beat Value: " + this.heartRate)

    });

    this.lineChartLabels = this.lineChartLabels.slice(1);

    this.lineChartLabels.push(`Label ${this.lineChartLabels.length}`);
    ;
  }

  //####################################################################################################

  private stompClient = null;
  patient;
  //bp;
  //heartRate;
  ecg;
  greetings: string[] = new Array();
  disabled = true;

  setConnected(connected: boolean) {
    this.disabled = !connected;
 
    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    let that = this;
    that.stompClient = Stomp.Stomp.client("ws://localhost:9999/greeting");

    that.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe('/topic/heartRate', function (message) {
        //console.log("Message: " + message);
        //console.log("Message Body: " + message.body);
        that.greetings.push(message.body);
        that.heartRate = (message.body);
      });
  
  
      that.stompClient.subscribe('/topic/ecg', function (message) {
        that.greetings.push(message.body);
        that.ecg = message.body;
      });
  
  
      that.stompClient.subscribe('/topic/bp', function (message) {
        that.greetings.push(message.body);
        console.log("message.body..."+message.body)
        that.bp = Number(message.body);

      });
 
      let data = JSON.stringify({
        'name': 'Hari'
      })
      that.stompClient.send("/app/message", {}, data);

      //this.lineChartData[0].data.push(this.bp)

      // this.lineChartData.forEach((x, i) => {
      // const num = this.bp;
      // x.data = x.data.slice(i)
      // const data: number[] = x.data as number[];
      // data.push(num);
      //});
      // this.lineChartLabels = this.lineChartLabels.slice(1);

      // this.lineChartLabels.push(`Label ${this.lineChartLabels.length}`);
    });
  }


  //  print() {
  //    console.log("BP Value is: " + this.bp);
  // }
}
