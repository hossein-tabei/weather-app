import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Next5daysStatusComponent } from './next5days-status.component';

describe('Next5daysStatusComponent', () => {
  let component: Next5daysStatusComponent;
  let fixture: ComponentFixture<Next5daysStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Next5daysStatusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Next5daysStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
