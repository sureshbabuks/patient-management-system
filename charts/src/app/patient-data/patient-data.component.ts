import { Component, OnInit, TemplateRef } from '@angular/core';
import {PatientDataService} from '../service/data/patient-data.service';
import {Router} from "@angular/router";
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { send } from 'q';
import { Observable, observable } from 'rxjs';

export class Patient {
  constructor(
    public firstName: string,
    public lastName: string,
    public gender: string,
  ) {

  }
}

@Component({
  selector: 'app-patient-data',
  templateUrl: './patient-data.component.html',
  styleUrls: ['./patient-data.component.css']
})
export class PatientDataComponent implements OnInit {

  patients = [];

  constructor(private router: Router, private patientDataService: PatientDataService, private modalService: BsModalService) { }

  ngOnInit() {
    this.patientDataService.getAllpatients().subscribe(
      response => {
        //console.log(response);
        this.patients = response;
      }
    );
  }

  onTimeOut() {
    //console.log("hello");
  }

  addPatient() {
    this.router.navigate(['patient']);
  }


  //////////////////////////////////////////////////////////////////////////////////////////

  private stompClient = null;
  patient;
  heartRate;
  bp;
  ecg;
  greetings: string[] = new Array();
  modalRef: BsModalRef;
  disabled = true;

  setConnected(connected: boolean) {
    this.disabled = !connected;
 
    if (connected) {
      this.greetings = [];
    }
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
    
  }

  connect() {
    this.router.navigate(["lineChart"])
  }

  disconnect() {
    this.modalRef.hide();
    console.log("HI disconnect");
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
 
    this.setConnected(false);
    console.log('Disconnected!');
  }

  send() {
    this.router.navigate(["lineChart",this.bp]);
  }


}
