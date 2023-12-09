import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Next24hoursStatusComponent } from './next24hours-status.component';

describe('Next24hoursStatusComponent', () => {
  let component: Next24hoursStatusComponent;
  let fixture: ComponentFixture<Next24hoursStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Next24hoursStatusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Next24hoursStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
