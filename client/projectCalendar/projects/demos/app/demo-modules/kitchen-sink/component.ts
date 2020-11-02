import {
  Component,
  ChangeDetectionStrategy,
  ViewChild,
  TemplateRef,
} from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours,
} from 'date-fns';
import { Subject } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient } from '@angular/common/http';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarView,
} from 'angular-calendar';
import { CalendarEventActionResponse } from './calendar-event-action-response';
import { CalendarEventActionsComponent } from 'projects/angular-calendar/src/modules/common/calendar-event-actions.component';
import { DatePipe } from '@angular/common';

const colors: any = {
  red: {
    primary: '#ad2121',
    secondary: '#FAE3E3',
  },
  blue: {
    primary: '#1e90ff',
    secondary: '#D1E8FF',
  },
  yellow: {
    primary: '#e3bc08',
    secondary: '#FDF1BA',
  },
};

@Component({
  selector: 'mwl-demo-component',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['styles.css'],
  templateUrl: 'template.html',
})
export class DemoComponent {
  @ViewChild('modalContent', { static: true }) modalContent: TemplateRef<any>;

  view: CalendarView = CalendarView.Month;

  CalendarView = CalendarView;

  viewDate: Date = new Date();

  modalData: {
    action: string;
    event: CalendarEvent;
  };

  actions: CalendarEventAction[] = [
    {
      label: '<i class="fas fa-fw fa-pencil-alt"></i>',
      a11yLabel: 'Edit',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.handleEvent('Edited', event);
      },
    },
    {
      label: '<i class="fas fa-fw fa-trash-alt"></i>',
      a11yLabel: 'Delete',
      onClick: ({ event }: { event: CalendarEvent }): void => {
        this.events = this.events.filter((iEvent) => iEvent !== event);
        this.handleEvent('Deleted', event);
      },
    },
  ];

  refresh: Subject<any> = new Subject();

  events: CalendarEvent[] = [];
  //   {
  //     start: subDays(startOfDay(new Date()), 1),
  //     end: addDays(new Date(), 1),
  //     title: 'A 3 day event',
  //     color: colors.red,
  //     actions: this.actions,
  //     allDay: true,
  //     resizable: {
  //       beforeStart: true,
  //       afterEnd: true,
  //     },
  //     draggable: true,
  //   },
  //   {
  //     start: startOfDay(new Date()),
  //     title: 'An event with no end date',
  //     color: colors.yellow,
  //     actions: this.actions,
  //   },
  //   {
  //     start: subDays(endOfMonth(new Date()), 3),
  //     end: addDays(endOfMonth(new Date()), 3),
  //     title: 'A long event that spans 2 months',
  //     color: colors.blue,
  //     allDay: true,
  //   },
  //   {
  //     start: addHours(startOfDay(new Date()), 2),
  //     end: addHours(new Date(), 2),
  //     title: 'A draggable and resizable event',
  //     color: colors.yellow,
  //     actions: this.actions,
  //     resizable: {
  //       beforeStart: true,
  //       afterEnd: true,
  //     },
  //     draggable: true,
  //   },
  // ];

  activeDayIsOpen: boolean = true;

  constructor(
    private http: HttpClient,
    private modal: NgbModal,
    public datepipe: DatePipe
  ) {
    const params = new HttpParams();

    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('Access-Control-Allow-Headers', 'Content-Type')
      .append('Access-Control-Allow-Methods', 'GET')
      .append('Access-Control-Allow-Origin', '*');

    this.http
      .get('http://localhost:8081/getAllEvents', { headers, params })
      .subscribe((data: CalendarEventActionResponse[]) => {
        data.forEach((childObj) => {
          console.log('child start: ' + childObj.start);
          console.log('child end: ' + childObj.end);

          var event: CalendarEvent = {
            id: childObj.id,
            title: childObj.title,
            color: colors.blue,
            allDay: childObj.allDay,
            start: new Date(childObj.start),
            end: new Date(childObj.end),
          };

          this.events.push(event);
          // this.addEventWithParams(childObj.title, childObj.start, childObj.end, "test");

          // console.log("bbbbbbbbbbbbb: "+childObj.title);
        });
      });

    console.log('Start constructor and load events....');
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if (
        (isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) ||
        events.length === 0
      ) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
      }
      this.viewDate = date;
    }
  }

  eventTimesChanged({
    event,
    newStart,
    newEnd,
  }: CalendarEventTimesChangedEvent): void {
    this.events = this.events.map((iEvent) => {
      if (iEvent === event) {
        return {
          ...event,
          start: newStart,
          end: newEnd,
        };
      }
      return iEvent;
    });
    this.handleEvent('Dropped or resized', event);
  }

  handleEvent(action: string, event: CalendarEvent): void {
    this.modalData = { event, action };
    this.modal.open(this.modalContent, { size: 'lg' });
  }

  addEvent(): void {
    this.events = [
      ...this.events,
      {
        title: 'New event',
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  addEventWithParams(
    _title: string,
    _start: string,
    _end: string,
    _color: string
  ): void {
    this.events = [
      ...this.events,
      {
        title: _title,
        start: startOfDay(new Date()),
        end: endOfDay(new Date()),
        color: colors.red,
        draggable: true,
        resizable: {
          beforeStart: true,
          afterEnd: true,
        },
      },
    ];
  }

  deleteEvent(eventToDelete: CalendarEvent) {
    this.events = this.events.filter((event) => event !== eventToDelete);

    const params = new HttpParams().set('id', '' + eventToDelete.id);

    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('Access-Control-Allow-Headers', 'Content-Type')
      .append('Access-Control-Allow-Methods', 'GET')
      .append('Access-Control-Allow-Origin', '*');

    this.http
      .get('http://localhost:8081/allEvents', {
        headers,
        responseType: 'text',
        params,
      })
      .subscribe();
  }

  saveEvent(eventToSave: CalendarEvent) {
    console.log(
      'date start: ' +
        this.datepipe.transform(eventToSave.start, 'yyyy-MM-ddThh:mm:ss')
    );
    console.log(
      'date end: ' +
        this.datepipe.transform(eventToSave.end, 'yyyy-MM-ddThh:mm:ss')
    );

    const params = new HttpParams()
      .set('title', eventToSave.title)

      .set(
        'start',
        '' + this.datepipe.transform(eventToSave.start, 'yyyy-MM-ddThh:mm:ss')
      )
      .set(
        'end',
        '' + this.datepipe.transform(eventToSave.end, 'yyyy-MM-ddThh:mm:ss')
      )

      // 1968-11-16T00:00:00

      .set('allDay', '' + eventToSave.allDay)
      .set('colorPrimary', eventToSave.color.primary)
      .set('colorSecondary', eventToSave.color.secondary)
      .set('id', '' + eventToSave.id);

    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json')
      .append('Access-Control-Allow-Headers', 'Content-Type')
      .append('Access-Control-Allow-Methods', 'GET')
      .append('Access-Control-Allow-Origin', '*');

    this.http
      .get('http://localhost:8081/saveEvent', {
        headers,
        responseType: 'text',
        params,
      })
      .subscribe((res) => console.log(res));

    console.log('Saved Event on DB');
  }

  setView(view: CalendarView) {
    this.view = view;
  }

  closeOpenMonthViewDay() {
    this.activeDayIsOpen = false;
  }
}
