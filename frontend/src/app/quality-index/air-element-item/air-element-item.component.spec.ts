import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirElementItemComponent } from './air-element-item.component';

describe('AirElementItemComponent', () => {
  let component: AirElementItemComponent;
  let fixture: ComponentFixture<AirElementItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AirElementItemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AirElementItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
